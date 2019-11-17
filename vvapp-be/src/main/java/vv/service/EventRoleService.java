package vv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.model.EventRole;
import vv.repository.EventRoleRepository;

import java.util.List;

@Service
public class EventRoleService {
    @Autowired
    EventRoleRepository eventRoleRepository;

    public List<EventRole> getAllEventRoles() {return eventRoleRepository.findAll();}

    public EventRole getEventRoleById(long id) {
        return eventRoleRepository.findById(id).orElse(null);
    }

    public void saveEventRole(EventRole eventRole) {
        eventRoleRepository.save(eventRole);
    }
}
