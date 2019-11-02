package vv.repository;

import org.springframework.data.repository.CrudRepository;
import vv.model.Senior;

import java.util.List;

public interface SeniorRepository extends CrudRepository<Senior, Long> {
    List<Senior> findAll();
}
