package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vv.dto.SeniorDTO;
import vv.dto.SeniorDetailDTO;
import vv.helper.mapper.SeniorMapper;
import vv.model.AuthSchResponse;
import vv.model.AuthSchTokenResponse;
import vv.model.Senior;
import vv.model.SeniorGroup;
import vv.service.AuthSchService;
import vv.service.SeniorGroupService;
import vv.service.SeniorService;
import vv.service.UserRoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://152.66.178.92:3000", allowCredentials = "true")
@RequestMapping("/user")
public class UserResource {

    @Autowired
    SeniorService seniorService;

    @Autowired
    AuthSchService authSchService;

    @Autowired
    SeniorGroupService seniorGroupService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    SeniorMapper seniorMapper;

    //TODO implement autentication
    @GetMapping(value = "/current")
    public SeniorDTO getCurrentUser(@RequestParam(value="authorizationCode", required = false)String authorizationCode, HttpServletRequest request, HttpSession httpSession) {
        httpSession.invalidate();
        request.getSession();

        if(!this.authSchService.hasAccessToken())
            authSchService.getToken(authorizationCode);

        AuthSchResponse authSchResponse = authSchService.getData();

        Senior senior = seniorService.getSeniorByAuthSchId(authSchResponse.getInternal_id());
        if(senior == null){
            senior = seniorService.getSeniorByEmail(authSchResponse.getMail());
            if(senior == null){
                senior = new Senior();
                senior.setEmail(authSchResponse.getMail());
                senior.setName(authSchResponse.getDisplayName());
                senior.setMobile(authSchResponse.getMobile());
                senior.setGroup(seniorGroupService.getAllGroups().get(0));
                senior.setUserRole(userRoleService.getAllUserRoles().get(0));

            }
            senior.setAuthSchId(authSchResponse.getInternal_id());
        }
        senior.setLastLogin(new Date());
        senior = seniorService.saveSenior(senior);

        return seniorMapper.seniorToSeniorDto(senior);
    }
}
