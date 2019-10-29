package vv.resource;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
}
