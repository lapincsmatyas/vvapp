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

import java.util.Date;

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

        Senior senior = seniorService.getSeniorByAuthSchId(authSchResponse.getInternal_id());
        if(senior == null){
            senior = seniorService.getSeniorByEmail(authSchResponse.getMail());
            if(senior == null){
                senior = new Senior();
                senior.setEmail(authSchResponse.getMail());
                senior.setName(authSchResponse.getDisplayName());
                senior.setMobile(authSchResponse.getMobile());
            } else{
                senior.setAuthSchId(authSchResponse.getInternal_id());
            }
        }
        senior.setLastLogin(new Date());
        senior = seniorService.saveSenior(senior);

        return SeniorMapper.INSTANCE.seniorToSeniorDto(senior);
    }
}
