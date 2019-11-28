package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vv.dto.ParticipationDTO;
import vv.dto.SeniorDTO;
import vv.dto.SeniorDetailDTO;
import vv.helper.mapper.ParticipationMapper;
import vv.helper.mapper.SeniorMapper;
import vv.model.Participation;
import vv.model.Senior;
import vv.service.ParticipationService;
import vv.service.SeniorService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    SeniorService seniorService;

    //TODO implement autentication
    @GetMapping(value = "/current")
    public SeniorDetailDTO getCurrentUser() {
        List<Senior> seniors = seniorService.getAllSeniors();
        if(seniors.size() == 0)
            return null;
        else
            return SeniorMapper.INSTANCE.seniorToSeniorDetailDto(seniors.get(0));
    }
}
