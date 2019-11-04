package vv.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vv.model.Event;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAll();

    @Query("SELECT e FROM Event e WHERE e.eventType.eventTypeId = :id")
    List<Event> findByEventTypeId(long id);
}
