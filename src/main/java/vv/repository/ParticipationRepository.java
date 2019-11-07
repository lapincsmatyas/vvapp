package vv.repository;

import org.springframework.data.repository.CrudRepository;
import vv.model.Participation;

import java.util.List;

public interface ParticipationRepository extends CrudRepository<Participation, Long> {
    List<Participation> findAll();
}
