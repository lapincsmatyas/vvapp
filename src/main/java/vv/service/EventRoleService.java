package vv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.model.EventRole;
import vv.model.EventType;
import vv.repository.EventRoleRepository;
import vv.repository.EventTypeRepository;

import java.util.List;

@Service
public class EventRoleService {
    @Autowired
    EventRoleRepository eventRoleRepository;

    public EventRole getEventRoleById(long id) {
        return eventRoleRepository.findById(id).orElse(null);
    }
}
