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
import vv.model.*;
import vv.service.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/event-type")
public class EventTypeResource {

    @Autowired
    EventTypeService eventTypeService;

    @Autowired
    EventRoleService eventRoleService;

    @Autowired
    EventService eventService;

    @Autowired
    SeniorService seniorService;

    @Autowired
    AuthSchTokenResponse authSchTokenResponse;

    @Autowired
    AuthSchService authSchService;

    @GetMapping
    public List<EventTypeDTO> getAllEventTypes(){
        List<EventType> eventTypes = eventTypeService.getAllEventTypes();
        return eventTypes.stream().map(EventTypeMapper.INSTANCE::eventTypeToEventTypeDto).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity addEventType(@RequestBody EventTypeDTO eventTypeDTO){
        Senior actSenior = seniorService.getSeniorByAuthSchId(authSchService.getData(authSchTokenResponse).getInternal_id());
        if(!(actSenior.getUserRole().getName().equals("VÁRÚR"))){
            return new ResponseEntity<>("Only ADMIN users can create event roles!", HttpStatus.UNAUTHORIZED);
        }

        EventType eventType = EventTypeMapper.INSTANCE.eventTypeDtoToEventType(eventTypeDTO);
        eventTypeService.saveEventType(eventType);
        return new ResponseEntity<>(EventTypeMapper.INSTANCE.eventTypeToEventTypeDto(eventType), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/events")
    public List<EventDTO> getEventsForEventType(@PathVariable long id){
        List<Event> events = eventService.getEventsByEventTypeId(id);
        return events.stream().map(EventMapper.INSTANCE::eventToEventDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public EventTypeDetailDTO getEventTypeById(@PathVariable long id){
        EventTypeDetailDTO eventTypeDetailDTO =
                EventTypeMapper.INSTANCE.eventTypeToEventTypeDetailDto(eventTypeService.getEventTypeById(id));
        eventTypeDetailDTO.setEvents(
                eventService.getEventsByEventTypeId(id).stream().map(
                        EventMapper.INSTANCE::eventToEventDto).collect(Collectors.toList()));
        return eventTypeDetailDTO;
    }
}
