package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.dto.ParticipationDTO;
import vv.dto.SeniorDTO;
import vv.dto.SeniorDetailDTO;
import vv.helper.mapper.ParticipationMapper;
import vv.helper.mapper.SeniorGroupMapper;
import vv.helper.mapper.SeniorMapper;
import vv.helper.mapper.UserRoleMapper;
import vv.model.AuthSchResponse;
import vv.model.AuthSchTokenResponse;
import vv.model.Participation;
import vv.model.Senior;
import vv.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/senior")
public class SeniorResource {

    @Autowired
    SeniorService seniorService;

    @Autowired
    ParticipationService participationService;

    @Autowired
    AuthSchTokenResponse authSchTokenResponse;

    @Autowired
    AuthSchService authSchService;

    @Autowired
    SeniorGroupService seniorGroupService;

    @Autowired
    UserRoleService userRoleService;

    @GetMapping
    public List<SeniorDTO> getAllSeniors(){
        List<Senior> seniors = seniorService.getAllSeniors();
        return seniors.stream().map(SeniorMapper.INSTANCE::seniorToSeniorDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public SeniorDetailDTO getSeniorById(@PathVariable("id") long id) {
        Senior senior = seniorService.getSeniorById(id);
        if (senior != null) {
            SeniorDetailDTO seniorDetailDTO = SeniorMapper.INSTANCE.seniorToSeniorDetailDto(senior);
            seniorDetailDTO.setParticipations(
                    senior.getParticipations().stream().map(
                            SeniorMapper.INSTANCE::participationToParticipationDto).collect(Collectors.toList()));
            return seniorDetailDTO;
        }
        else return null;
    }

    @PostMapping
    public ResponseEntity addSenior(@RequestBody SeniorDTO seniorDTO){
        Senior actSenior = seniorService.getSeniorByAuthSchId(authSchService.getData().getInternal_id());
        if(!(actSenior.getUserRole().getName().equals("VÁRÚR"))){
            return new ResponseEntity<>("Only ADMIN users can add events to seniors!",HttpStatus.UNAUTHORIZED);
        }

        Senior senior = SeniorMapper.INSTANCE.seniorDtoToSenior(seniorDTO);
        senior.setGroup(actSenior.getGroup());
        senior.setUserRole(userRoleService.getAllUserRoles().get(0));
        seniorService.saveSenior(senior);
        return new ResponseEntity<>(SeniorMapper.INSTANCE.seniorToSeniorDto(senior), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/participations")
    public List<ParticipationDTO> getParticipationsOfSenior(@PathVariable("id") long id){
        Senior senior = seniorService.getSeniorById(id);
        if(senior != null){
            List<Participation> participations = new ArrayList<>(senior.getParticipations());
            return participations.stream().map(ParticipationMapper.INSTANCE::participationToParticipationDto).collect(Collectors.toList());
        }
        return null;
    }

    @PostMapping(value = "/{seniorId}/events")
    public ResponseEntity addEventToSenior(@PathVariable("seniorId") long seniorId, @RequestParam long eventId, @RequestParam long eventRoleId){
        Senior actSenior = seniorService.getSeniorByAuthSchId(authSchService.getData().getInternal_id());
        Senior senior = seniorService.getSeniorById(seniorId);
        if(!(actSenior.getUserRole().getName().equals("VÁRÚR"))){
            return new ResponseEntity<>("Only ADMIN users can add events to seniors!",HttpStatus.UNAUTHORIZED);
        }
        if(!(actSenior.getGroup().getGroupId().equals(senior.getGroup().getGroupId()))){
            return new ResponseEntity<>("ADMINS can edit only the seniors in their group!",HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(ParticipationMapper.INSTANCE.participationToParticipationDto(
                participationService.createParticipation(eventId, seniorId, eventRoleId)), HttpStatus.OK);
    }

    @PatchMapping(value = "/{seniorId}")
    public ResponseEntity patchSenior(@PathVariable("seniorId") long seniorId, @RequestBody SeniorDTO seniorDTO){
        Senior actSenior = seniorService.getSeniorByAuthSchId(authSchService.getData().getInternal_id());
        Senior senior = seniorService.getSeniorById(seniorId);
        if(!actSenior.getUserRole().getName().equals("VÁRÚR") && !senior.getSeniorId().equals(actSenior.getSeniorId())){
            return new ResponseEntity<>("Only ADMINS and profile owners can edit profiles!",HttpStatus.UNAUTHORIZED);
        }

        if(seniorDTO.getName() != null) senior.setName(seniorDTO.getName());
        if(seniorDTO.getEmail() != null) senior.setEmail(seniorDTO.getEmail());
        if(seniorDTO.getGroup() != null) senior.setGroup(SeniorGroupMapper.INSTANCE.groupDtoToGroup(seniorDTO.getGroup()));
        if(seniorDTO.getMobile() != null) senior.setMobile(seniorDTO.getMobile());
        if(seniorDTO.getUserRole() != null) senior.setUserRole(UserRoleMapper.INSTANCE.userRoleDtoToUserRole(seniorDTO.getUserRole()));
        return new ResponseEntity<>(SeniorMapper.INSTANCE.seniorToSeniorDto(seniorService.saveSenior(senior)), HttpStatus.OK);
    }
}
