package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.dto.EventRoleDTO;
import vv.helper.mapper.EventRoleMapper;
import vv.model.AuthSchTokenResponse;
import vv.model.EventRole;
import vv.model.EventType;
import vv.model.Senior;
import vv.service.AuthSchService;
import vv.service.EventRoleService;
import vv.service.EventTypeService;
import vv.service.SeniorService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://152.66.178.92:3000", allowCredentials = "true")
@RestController
@RequestMapping("/event-role")
public class EventRoleResource {

    @Autowired
    EventRoleService eventRoleService;

    @Autowired
    EventTypeService eventTypeService;

    @Autowired
    SeniorService seniorService;

    @Autowired
    AuthSchService authSchService;

    @Autowired
    AuthSchTokenResponse authSchTokenResponse;

    @GetMapping
    public List<EventRoleDTO> getAllEventRoles(){
        List<EventRole> eventRoles = eventRoleService.getAllEventRoles();
        List<EventRoleDTO> eventRoleDTOS = eventRoles.stream().map(EventRoleMapper.INSTANCE::eventRoleToEventRoleDto).collect(Collectors.toList());
        return eventRoleDTOS;
    }

    @PostMapping
    public ResponseEntity addEventRole(@RequestBody EventRoleDTO eventRoleDTO){
        Senior actSenior = seniorService.getSeniorByAuthSchId(authSchService.getData().getInternal_id());
        if(!(actSenior.getUserRole().getName().equals("VÁRÚR"))){
            return new ResponseEntity<>("Only ADMIN users can create event roles!", HttpStatus.UNAUTHORIZED);
        }

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
