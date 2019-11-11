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
import vv.helper.mapper.SeniorMapper;
import vv.model.Participation;
import vv.model.Senior;
import vv.service.ParticipationService;
import vv.service.SeniorService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/senior")
public class SeniorResource {

    @Autowired
    SeniorService seniorService;

    @Autowired
    ParticipationService participationService;

    @GetMapping
    public ResponseEntity<List<SeniorDTO>> getAllSeniors(){
        List<Senior> seniors = seniorService.getAllSeniors();
        return new ResponseEntity<>(seniors.stream().map(SeniorMapper.INSTANCE::seniorToSeniorDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SeniorDetailDTO> getSeniorById(@PathVariable("id") long id) {
        Senior senior = seniorService.getSeniorById(id);
        if (senior != null) {
            SeniorDetailDTO seniorDetailDTO = SeniorMapper.INSTANCE.seniorToSeniorDetailDto(senior);
            seniorDetailDTO.setParticipations(
                    senior.getParticipations().stream().map(
                            SeniorMapper.INSTANCE::participationToParticipationDto).collect(Collectors.toList()));
            return new ResponseEntity<>(seniorDetailDTO, HttpStatus.OK);
        }
        else return null;
    }

    @PostMapping
    public ResponseEntity<SeniorDTO> addSenior(@RequestBody SeniorDTO seniorDTO){
        Senior senior = SeniorMapper.INSTANCE.seniorDtoToSenior(seniorDTO);
        seniorService.saveSenior(senior);
        return new ResponseEntity<>(SeniorMapper.INSTANCE.seniorToSeniorDto(senior), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/participations")
    public ResponseEntity<List<ParticipationDTO>> getParticipationsOfSenior(@PathVariable("id") long id){
        Senior senior = seniorService.getSeniorById(id);
        if(senior != null){
            List<Participation> participations = new ArrayList<>(senior.getParticipations());
            return new ResponseEntity<>(participations.stream().map(ParticipationMapper.INSTANCE::participationToParticipationDto).collect(Collectors.toList()), HttpStatus.OK);
        }
        return null;
    }

    @PostMapping(value = "/{seniorId}/events")
    public ResponseEntity<ParticipationDTO> addEventToSenior(@PathVariable("seniorId") long seniorId, @RequestParam long eventId, @RequestParam long eventRoleId){
        return new ResponseEntity<>(ParticipationMapper.INSTANCE.participationToParticipationDto(
                participationService.createParticipation(eventId, seniorId, eventRoleId)), HttpStatus.OK);
    }
}
