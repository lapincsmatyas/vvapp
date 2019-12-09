package vv.service;

import com.sun.net.httpserver.HttpsParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.util.UriComponentsBuilder;
import vv.model.AuthSchResponse;
import vv.model.AuthSchTokenResponse;

import java.io.Serializable;
import java.util.*;

@Service
public class AuthSchService {

    @Value("${authsch.client_id}")
    private String client_id;

    @Value("${authsch.client_key}")
    private String client_key;

    @Autowired
    AuthSchTokenResponse authSchTokenResponse;

    private String credentials;

    public void getToken(String authorizationCode){
        HttpHeaders headers = new HttpHeaders();

        credentials =  client_id + ":" + client_key;
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization","Basic " + new String(Base64.getEncoder().encode(credentials.getBytes())));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String access_token_url = "https://auth.sch.bme.hu/oauth2/token";

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "authorization_code");
        map.add("code", authorizationCode);

        HttpEntity request = new HttpEntity(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity result = restTemplate.exchange(access_token_url, HttpMethod.POST, request, AuthSchTokenResponse.class);
        AuthSchTokenResponse authSchTokenResponse= (AuthSchTokenResponse)result.getBody();
        this.authSchTokenResponse.setAccess_token(authSchTokenResponse.getAccess_token());
        this.authSchTokenResponse.setExpires_in(authSchTokenResponse.getExpires_in());
        this.authSchTokenResponse.setRefresh_token(authSchTokenResponse.getRefresh_token());
        this.authSchTokenResponse.setScope(authSchTokenResponse.getScope());
        this.authSchTokenResponse.setToken_type(authSchTokenResponse.getToken_type());
    }

    public AuthSchResponse getData(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String api_url = "https://auth.sch.bme.hu/api/profile/";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(api_url)
                .queryParam("access_token", authSchTokenResponse.getAccess_token());

        HttpEntity request = new HttpEntity(headers);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);

        return restTemplate.getForObject(builder.toUriString(), AuthSchResponse.class);
    }
}
