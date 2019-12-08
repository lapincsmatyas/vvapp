package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.dto.EventRoleDTO;
import vv.helper.mapper.EventRoleMapper;
import vv.model.EventRole;
import vv.service.EventRoleService;
import vv.service.EventTypeService;
import vv.service.SeniorService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/event-role")
public class EventRoleResource {

    @Autowired
    EventRoleService eventRoleService;

    @Autowired
    EventTypeService eventTypeService;

    @Autowired
    SeniorService seniorService;

    @GetMapping
    public List<EventRoleDTO> getAllEventRoles(){
        List<EventRole> eventRoles = eventRoleService.getAllEventRoles();
        List<EventRoleDTO> eventRoleDTOS = eventRoles.stream().map(EventRoleMapper.INSTANCE::eventRoleToEventRoleDto).collect(Collectors.toList());
        return eventRoleDTOS;
    }

    @PostMapping
    public ResponseEntity addEventRole(@RequestBody EventRoleDTO eventRoleDTO){
           EventRole eventRole = EventRoleMapper.INSTANCE.eventRoleDtoToEventType(eventRoleDTO);
        eventRoleService.saveEventRole(eventRole);
        return new ResponseEntity<>(EventRoleMapper.INSTANCE.eventRoleToEventRoleDto(eventRole), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public EventRoleDTO getEventRoleById(@PathVariable long id){
        EventRole eventRole = eventRoleService.getEventRoleById(id);
        return EventRoleMapper.INSTANCE.eventRoleToEventRoleDto(eventRole);
    }
}
