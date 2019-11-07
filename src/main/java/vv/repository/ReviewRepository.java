package vv.repository;

import org.springframework.data.repository.CrudRepository;
import vv.model.Review;
import vv.model.Senior;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAll();
}
