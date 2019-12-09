package vv.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vv.model.Senior;
import vv.model.SeniorGroup;

import java.util.List;

public interface SeniorRepository extends JpaRepository<Senior, Long> {
    Senior findByAuthSchId(String authSchId);
    Senior findByEmail(String email);

    @Query("SELECT s FROM Senior s WHERE s.group = :group AND s.userRole.name = 'VÁRÚR'")
    List<Senior> findAdminsOfGroup(@Param("group") SeniorGroup group);
}
