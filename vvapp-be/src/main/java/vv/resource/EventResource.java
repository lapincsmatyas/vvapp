package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.dto.EventDTO;
import vv.dto.EventDetailDTO;
import vv.dto.ParticipationDTO;
import vv.helper.mapper.EventMapper;
import vv.helper.mapper.ParticipationMapper;
import vv.model.*;
import vv.repository.EventRoleRepository;
import vv.service.AuthSchService;
import vv.service.EventService;
import vv.service.ParticipationService;
import vv.service.SeniorService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/event")
public class EventResource {

    @Autowired
    EventService eventService;

    @Autowired
    SeniorService seniorService;

    @Autowired
    EventRoleRepository eventRoleRepository;

    @Autowired
    ParticipationService participationService;

    @Autowired
    AuthSchService authSchService;

    @Autowired
    AuthSchTokenResponse authSchTokenResponse;

    @GetMapping
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return events.stream().map(EventMapper.INSTANCE::eventToEventDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public EventDetailDTO getEventById(@PathVariable("id") long id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return EventMapper.INSTANCE.eventToEventDetailDto(event);
        } else return null;
    }

    @PostMapping
    public ResponseEntity addEvent(@RequestBody EventDTO eventDTO) {
        Senior actSenior = seniorService.getSeniorByAuthSchId(authSchService.getData(authSchTokenResponse).getInternal_id());
        if(!(actSenior.getUserRole().getName().equals("VÁRÚR"))){
            return new ResponseEntity<>("Only ADMIN users can create events!",HttpStatus.UNAUTHORIZED);
        }

        Event event = EventMapper.INSTANCE.eventDtoToEvent(eventDTO);
        eventService.saveEvent(event);

        return new ResponseEntity<>(EventMapper.INSTANCE.eventToEventDto(event), HttpStatus.OK);
    }

    @PostMapping(value = "/{eventId}/seniors")
    public ResponseEntity addSeniorToEvent(@PathVariable long eventId, @RequestParam long seniorId, @RequestParam long eventRoleId) {
        Senior actSenior = seniorService.getSeniorByAuthSchId(authSchService.getData(authSchTokenResponse).getInternal_id());
        Senior senior = seniorService.getSeniorById(seniorId);
        if(!(actSenior.getUserRole().getName().equals("VÁRÚR"))){
            return new ResponseEntity<>("Only ADMIN users can add seniors to event!",HttpStatus.UNAUTHORIZED);
        }
        if(!(actSenior.getGroup().getGroupId().equals(senior.getGroup().getGroupId()))){
            return new ResponseEntity<>("ADMINS can edit only the seniors in their group!",HttpStatus.UNAUTHORIZED);
        }

        Participation participation = participationService.createParticipation(eventId, seniorId, eventRoleId);
        return new ResponseEntity<>(ParticipationMapper.INSTANCE.participationToParticipationDto(participation), HttpStatus.OK);
    }
}
