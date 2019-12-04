package vv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vv.model.SeniorGroup;
import vv.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
