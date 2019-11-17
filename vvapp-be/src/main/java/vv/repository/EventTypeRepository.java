package vv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vv.model.EventType;

import java.util.List;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
