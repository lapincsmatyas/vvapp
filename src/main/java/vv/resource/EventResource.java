package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vv.model.Event;
import vv.repository.EventRepository;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventResource {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Event getEventById(@PathVariable("id") long id){
        return eventRepository.findById(id).orElse(new Event());
    }
}
