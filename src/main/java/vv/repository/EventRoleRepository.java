package vv.repository;

import org.springframework.data.repository.CrudRepository;
import vv.model.Event;
import vv.model.EventRole;

import java.util.List;

public interface EventRoleRepository extends CrudRepository<EventRole, Long> {
    List<EventRole> findAll();
}
