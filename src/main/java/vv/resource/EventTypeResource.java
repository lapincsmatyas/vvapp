package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vv.dto.EventDTO;
import vv.dto.EventTypeDTO;
import vv.dto.EventTypeDetailDTO;
import vv.helper.mapper.EventMapper;
import vv.helper.mapper.EventTypeMapper;
import vv.model.Event;
import vv.model.EventType;
import vv.repository.EventTypeRepository;
import vv.service.EventService;
import vv.service.EventTypeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event-type")
public class EventTypeResource {

    @Autowired
    EventTypeService eventTypeService;

    @Autowired
    EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EventTypeDTO> getAllEventTypes(){
        List<EventType> eventTypes = eventTypeService.getAllEventTypes();
        return eventTypes.stream().map(EventTypeMapper.INSTANCE::eventTypeToEventTypeDto).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public EventTypeDTO addEventType(@RequestBody EventTypeDTO eventTypeDTO){
        EventType eventType = EventTypeMapper.INSTANCE.eventTypeDtoToEventType(eventTypeDTO);
        eventTypeService.saveEventType(eventType);
        return EventTypeMapper.INSTANCE.eventTypeToEventTypeDto(eventType);
    }

    @RequestMapping(value = "/{id}/events", method = RequestMethod.GET)
    public List<EventDTO> getEventsForEventType(@PathVariable long id){
        List<Event> events = eventService.getEventsByEventTypeId(id);
        return events.stream().map(EventMapper.INSTANCE::eventToEventDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EventTypeDetailDTO getEventTypeById(@PathVariable long id){
        EventTypeDetailDTO eventTypeDetailDTO =
                EventTypeMapper.INSTANCE.eventTypeToEventTypeDetailDto(eventTypeService.getEventTypeById(id));
        eventTypeDetailDTO.setEvents(
                eventService.getEventsByEventTypeId(id).stream().map(
                        EventMapper.INSTANCE::eventToEventDto).collect(Collectors.toList()));
        return eventTypeDetailDTO;
    }
}
