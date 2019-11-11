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
import vv.model.Event;
import vv.model.Participation;
import vv.repository.EventRoleRepository;
import vv.service.EventService;
import vv.service.ParticipationService;
import vv.service.SeniorService;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events.stream().map(EventMapper.INSTANCE::eventToEventDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventDetailDTO> getEventById(@PathVariable("id") long id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return new ResponseEntity<>(EventMapper.INSTANCE.eventToEventDetailDto(event), HttpStatus.OK);
        } else return null;
    }

    @PostMapping
    public ResponseEntity<EventDTO> addEvent(@RequestBody EventDTO eventDTO) {
        Event event = EventMapper.INSTANCE.eventDtoToEvent(eventDTO);
        eventService.saveEvent(event);
        return new ResponseEntity(EventMapper.INSTANCE.eventToEventDto(event), HttpStatus.OK);
    }

    @PostMapping(value = "/{eventId}/seniors")
    public ResponseEntity<ParticipationDTO> addSeniorToEvent(@PathVariable long eventId, @RequestParam long seniorId, @RequestParam long eventRoleId) {
        Participation participation = participationService.createParticipation(eventId, seniorId, eventRoleId);
        return new ResponseEntity<>(ParticipationMapper.INSTANCE.participationToParticipationDto(participation), HttpStatus.OK);
    }
}
