package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vv.dto.EventDTO;
import vv.dto.ParticipationDTO;
import vv.dto.SeniorDTO;
import vv.dto.SeniorDetailDTO;
import vv.helper.mapper.ParticipationMapper;
import vv.helper.mapper.SeniorMapper;
import vv.model.Event;
import vv.model.Participation;
import vv.model.Senior;
import vv.repository.SeniorRepository;
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

    @RequestMapping(method = RequestMethod.GET)
    public List<SeniorDTO> getAllSeniors(){
        List<Senior> seniors = seniorService.getAllSeniors();
        return seniors.stream().map(SeniorMapper.INSTANCE::seniorToSeniorDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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

    @RequestMapping(method = RequestMethod.POST)
    public SeniorDTO addSenior(@RequestBody SeniorDTO seniorDTO){
        Senior senior = SeniorMapper.INSTANCE.seniorDtoToSenior(seniorDTO);
        seniorService.saveSenior(senior);
        return SeniorMapper.INSTANCE.seniorToSeniorDto(senior);
    }

    @RequestMapping(value = "/{id}/participations", method = RequestMethod.GET)
    public List<ParticipationDTO> getParticipationsOfSenior(@PathVariable("id") long id){
        Senior senior = seniorService.getSeniorById(id);
        if(senior != null){
            List<Participation> participations = new ArrayList<>(senior.getParticipations());
            return participations.stream().map(ParticipationMapper.INSTANCE::participationToParticipationDto).collect(Collectors.toList());
        }
        return null;
    }

    @RequestMapping(value = "/{seniorId}/events", method = RequestMethod.POST)
    public ParticipationDTO addEventToSenior(@PathVariable("seniorId") long seniorId, @RequestParam long eventId, @RequestParam long eventRoleId){
        return ParticipationMapper.INSTANCE.participationToParticipationDto(
                participationService.createParticipation(eventId, seniorId, eventRoleId));
    }
}
