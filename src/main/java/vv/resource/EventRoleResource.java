package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.dto.EventRoleDTO;
import vv.helper.mapper.EventRoleMapper;
import vv.model.EventRole;
import vv.service.EventRoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event-role")
public class EventRoleResource {

    @Autowired
    EventRoleService eventRoleService;

    @GetMapping
    public ResponseEntity<List<EventRoleDTO>> getAllEventRoles(){
        List<EventRole> eventTypes = eventRoleService.getAllEventRoles();
        return new ResponseEntity<>(eventTypes.stream().map(EventRoleMapper.INSTANCE::eventRoleToEventRoleDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventRoleDTO> addEventRole(@RequestBody EventRoleDTO eventRoleDTO){
        EventRole eventRole = EventRoleMapper.INSTANCE.eventRoleDtoToEventType(eventRoleDTO);
        eventRoleService.saveEventRole(eventRole);
        return new ResponseEntity<>(EventRoleMapper.INSTANCE.eventRoleToEventRoleDto(eventRole), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventRoleDTO> getEventRoleById(@PathVariable long id){
        EventRole eventRole = eventRoleService.getEventRoleById(id);
        return new ResponseEntity<>(EventRoleMapper.INSTANCE.eventRoleToEventRoleDto(eventRole), HttpStatus.OK);
    }
}
