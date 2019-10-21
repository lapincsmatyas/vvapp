package vv.repository;

import org.springframework.data.repository.CrudRepository;
import vv.model.Event;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAll();
}
