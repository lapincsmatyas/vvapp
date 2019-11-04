package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.GET)
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return events.stream().map(EventMapper.INSTANCE::eventToEventDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EventDetailDTO getEventById(@PathVariable("id") long id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            return EventMapper.INSTANCE.eventToEventDetailDto(event);
        } else return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public EventDTO addEvent(@RequestBody EventDTO eventDTO) {
        Event event = EventMapper.INSTANCE.eventDtoToEvent(eventDTO);
        eventService.saveEvent(event);
        return EventMapper.INSTANCE.eventToEventDto(event);
    }

    @RequestMapping(value = "/{eventId}/seniors", method = RequestMethod.POST)
    public ParticipationDTO addSeniorToEvent(@PathVariable long eventId, @RequestParam long seniorId, @RequestParam long eventRoleId) {
        Participation participation = participationService.createParticipation(eventId, seniorId, eventRoleId);
        return ParticipationMapper.INSTANCE.participationToParticipationDto(participation);
    }
}
