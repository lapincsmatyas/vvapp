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

@RestController
@CrossOrigin(origins = "http://152.66.178.92:3000", allowCredentials = "true")
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

    @Autowired
    SeniorMapper seniorMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    ParticipationMapper participationMapper;

    @Autowired
    SeniorGroupMapper seniorGroupMapper;

    @GetMapping
    public List<SeniorDTO> getAllSeniors(){
        List<Senior> seniors = seniorService.getAllSeniors();
        return seniors.stream().map(seniorMapper::seniorToSeniorDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public SeniorDetailDTO getSeniorById(@PathVariable("id") long id) {
        Senior senior = seniorService.getSeniorById(id);
        if (senior != null) {
            SeniorDetailDTO seniorDetailDTO = seniorMapper.seniorToSeniorDetailDto(senior);
            seniorDetailDTO.setParticipations(
                    senior.getParticipations().stream().map(
                            seniorMapper::participationToParticipationDto).collect(Collectors.toList()));
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

        Senior senior = seniorMapper.seniorDtoToSenior(seniorDTO);
        senior.setGroup(actSenior.getGroup());
        senior.setUserRole(userRoleService.getAllUserRoles().get(0));
        seniorService.saveSenior(senior);
        return new ResponseEntity<>(seniorMapper.seniorToSeniorDto(senior), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/participations")
    public List<ParticipationDTO> getParticipationsOfSenior(@PathVariable("id") long id){
        Senior senior = seniorService.getSeniorById(id);
        if(senior != null){
            List<Participation> participations = new ArrayList<>(senior.getParticipations());
            return participations.stream().map(participationMapper::participationToParticipationDto).collect(Collectors.toList());
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

        return new ResponseEntity<>(participationMapper.participationToParticipationDto(
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
        if(seniorDTO.getGroup() != null) senior.setGroup(seniorGroupMapper.groupDtoToGroup(seniorDTO.getGroup()));
        if(seniorDTO.getMobile() != null) senior.setMobile(seniorDTO.getMobile());
        if(seniorDTO.getUserRole() != null) senior.setUserRole(userRoleMapper.userRoleDtoToUserRole(seniorDTO.getUserRole()));
        return new ResponseEntity<>(seniorMapper.seniorToSeniorDto(seniorService.saveSenior(senior)), HttpStatus.OK);
    }
}
