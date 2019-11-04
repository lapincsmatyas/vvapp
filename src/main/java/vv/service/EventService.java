package vv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.model.Event;
import vv.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event getEventById(long id){
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> getEventsByEventTypeId(long id){
        return eventRepository.findByEventTypeId(id);
    }

    public Event saveEvent(Event event){
        return eventRepository.save(event);
    }
}
