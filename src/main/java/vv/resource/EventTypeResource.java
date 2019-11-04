package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vv.dto.EventTypeDTO;
import vv.helper.mapper.EventTypeMapper;
import vv.model.Event;
import vv.model.EventType;
import vv.repository.EventTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event-type")
public class EventTypeResource {

    @Autowired
    EventTypeRepository eventTypeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<EventTypeDTO> getAllEventTypes(){
        List<EventType> eventTypes = eventTypeRepository.findAll();
        return eventTypes.stream().map(EventTypeMapper.INSTANCE::eventTypeToEventTypeDto).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public EventTypeDTO addEventType(@RequestBody EventTypeDTO eventTypeDTO){
        EventType eventType = EventTypeMapper.INSTANCE.eventTypeDtoToEventType(eventTypeDTO);
        eventTypeRepository.save(eventType);
        return EventTypeMapper.INSTANCE.eventTypeToEventTypeDto(eventType);
    }
}
