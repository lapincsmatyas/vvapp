package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vv.dto.EventDTO;
import vv.helper.mapper.EventMapper;
import vv.model.Event;
import vv.repository.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventResource {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<EventDTO> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventMapper.INSTANCE::eventToEventDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EventDTO getEventById(@PathVariable("id") long id){
        Event event = eventRepository.findById(id).orElse(null);
        if(event != null){
            return EventMapper.INSTANCE.eventToEventDto(event);
        }
        else return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public EventDTO addEvent(@RequestBody EventDTO eventDTO){
        Event event = EventMapper.INSTANCE.eventDtoToEvent(eventDTO);
        eventRepository.save(event);
        return EventMapper.INSTANCE.eventToEventDto(event);
    }
}
