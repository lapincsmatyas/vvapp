package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vv.dto.EventRoleDTO;
import vv.helper.mapper.EventRoleMapper;
import vv.model.EventRole;
import vv.service.EventRoleService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/event-role")
public class EventRoleResource {

    @Autowired
    EventRoleService eventRoleService;

    @GetMapping
    public List<EventRoleDTO> getAllEventRoles(){
        List<EventRole> eventRoles = eventRoleService.getAllEventRoles();
        List<EventRoleDTO> eventRoleDTOS = eventRoles.stream().map(EventRoleMapper.INSTANCE::eventRoleToEventRoleDto).collect(Collectors.toList());
        return eventRoleDTOS;
    }

    @PostMapping
    public EventRoleDTO addEventRole(@RequestBody EventRoleDTO eventRoleDTO){
        EventRole eventRole = EventRoleMapper.INSTANCE.eventRoleDtoToEventType(eventRoleDTO);
        eventRoleService.saveEventRole(eventRole);
        return EventRoleMapper.INSTANCE.eventRoleToEventRoleDto(eventRole);
    }

    @GetMapping(value = "/{id}")
    public EventRoleDTO getEventRoleById(@PathVariable long id){
        EventRole eventRole = eventRoleService.getEventRoleById(id);
        return EventRoleMapper.INSTANCE.eventRoleToEventRoleDto(eventRole);
    }
}
