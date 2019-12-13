package vv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vv.model.Senior;
import vv.model.SeniorGroup;

import java.util.List;

public interface SeniorRepository extends JpaRepository<Senior, Long> {
    Senior findByAuthSchId(String authSchId);
    Senior findByEmail(String email);

    @Query("SELECT s FROM Senior s WHERE s.group = :group AND s.userRole.name = 'VÁRÚR'")
    List<Senior> findAdminsOfGroup(@Param("group") SeniorGroup group);
}
