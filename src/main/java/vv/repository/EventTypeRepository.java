package vv.repository;

import org.springframework.data.repository.CrudRepository;
import vv.model.EventType;

import java.util.List;

public interface EventTypeRepository extends CrudRepository<EventType, Long> {
    List<EventType> findAll();
}
