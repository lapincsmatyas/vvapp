package vv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vv.model.SeniorGroup;

public interface SeniorGroupRepository extends JpaRepository<SeniorGroup, Long> {
}
