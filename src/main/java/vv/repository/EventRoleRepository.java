package vv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vv.model.EventRole;

import java.util.List;

public interface EventRoleRepository extends JpaRepository<EventRole, Long> {
}
