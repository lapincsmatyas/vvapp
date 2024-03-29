package vv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vv.model.Review;
import vv.model.Senior;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
