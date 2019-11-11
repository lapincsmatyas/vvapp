package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.dto.EventDTO;
import vv.dto.EventTypeDTO;
import vv.dto.EventTypeDetailDTO;
import vv.helper.mapper.EventMapper;
import vv.helper.mapper.EventTypeMapper;
import vv.model.Event;
import vv.model.EventType;
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

    @GetMapping
    public ResponseEntity<List<EventTypeDTO>> getAllEventTypes(){
        List<EventType> eventTypes = eventTypeService.getAllEventTypes();
        return new ResponseEntity<>(eventTypes.stream().map(EventTypeMapper.INSTANCE::eventTypeToEventTypeDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventTypeDTO> addEventType(@RequestBody EventTypeDTO eventTypeDTO){
        EventType eventType = EventTypeMapper.INSTANCE.eventTypeDtoToEventType(eventTypeDTO);
        eventTypeService.saveEventType(eventType);
        return new ResponseEntity<>(EventTypeMapper.INSTANCE.eventTypeToEventTypeDto(eventType), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/events")
    public ResponseEntity<List<EventDTO>> getEventsForEventType(@PathVariable long id){
        List<Event> events = eventService.getEventsByEventTypeId(id);
        return new ResponseEntity<>(events.stream().map(EventMapper.INSTANCE::eventToEventDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventTypeDetailDTO> getEventTypeById(@PathVariable long id){
        EventTypeDetailDTO eventTypeDetailDTO =
                EventTypeMapper.INSTANCE.eventTypeToEventTypeDetailDto(eventTypeService.getEventTypeById(id));
        eventTypeDetailDTO.setEvents(
                eventService.getEventsByEventTypeId(id).stream().map(
                        EventMapper.INSTANCE::eventToEventDto).collect(Collectors.toList()));
        return new ResponseEntity<>(eventTypeDetailDTO, HttpStatus.OK);
    }
}
