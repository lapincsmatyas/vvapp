package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vv.dto.SeniorDTO;
import vv.dto.SeniorDetailDTO;
import vv.helper.mapper.SeniorMapper;
import vv.model.AuthSchResponse;
import vv.model.AuthSchTokenResponse;
import vv.model.Senior;
import vv.service.AuthSchService;
import vv.service.SeniorService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    AuthSchTokenResponse authSchTokenResponse;

    @Autowired
    SeniorService seniorService;

    @Autowired
    AuthSchService authSchService;

    //TODO implement autentication
    @GetMapping(value = "/current")
    public SeniorDTO getCurrentUser(@RequestParam(value="authorizationCode", required = false)String authorizationCode) {
        if(this.authSchTokenResponse.getAccess_token() == null)
            authSchService.getToken(authorizationCode);

        AuthSchResponse authSchResponse = authSchService.getData(authSchTokenResponse);

        Senior senior = seniorService.getSeniorByEmail(authSchResponse.getMail());
        if(senior == null){
            senior = new Senior();
            senior.setEmail(authSchResponse.getMail());
            senior.setName(authSchResponse.getDisplayName());
            senior.setMobile(authSchResponse.getMobile());
        }
        senior = seniorService.saveSenior(senior);
        return SeniorMapper.INSTANCE.seniorToSeniorDto(senior);
    }
}
