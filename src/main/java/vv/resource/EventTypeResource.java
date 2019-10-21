package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vv.model.Event;
import vv.model.EventType;
import vv.repository.EventTypeRepository;

import java.util.List;

@RestController
@RequestMapping("/event-type")
public class EventTypeResource {

    @Autowired
    EventTypeRepository eventTypeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<EventType> getAllEvents(){
        return eventTypeRepository.findAll();
    }
}
