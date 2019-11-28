package vv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.model.EventRole;
import vv.model.EventType;
import vv.repository.EventTypeRepository;

import java.util.List;

@Service
public class EventTypeService {
    @Autowired
    EventTypeRepository eventTypeRepository;

    public List<EventType> getAllEventTypes(){
        return eventTypeRepository.findAll();
    }

    public EventType saveEventType(EventType eventType) {
        return eventTypeRepository.save(eventType);
    }

    public EventType getEventTypeById(long id) {
        EventType eventType = eventTypeRepository.findById(id).orElse(null);
        return eventType;
    }

    public EventType addEventRoleToEventType(EventType eventType, EventRole eventRole){
        eventType.getEventRoles().add(eventRole);
        return eventTypeRepository.save(eventType);
    }
}
