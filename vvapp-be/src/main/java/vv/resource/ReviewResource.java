package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.dto.ParticipationDTO;
import vv.dto.ReviewDTO;
import vv.dto.SeniorDTO;
import vv.dto.SeniorDetailDTO;
import vv.helper.mapper.ParticipationMapper;
import vv.helper.mapper.ReviewMapper;
import vv.helper.mapper.SeniorMapper;
import vv.model.Participation;
import vv.model.Review;
import vv.model.Senior;
import vv.service.ParticipationService;
import vv.service.ReviewService;
import vv.service.SeniorService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/review")
public class ReviewResource {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ParticipationService participationService;

    @GetMapping
    public List<ReviewDTO> getAllReviews(){
        List<Review> seniors = reviewService.getAllReviews();
        return seniors.stream().map(ReviewMapper.INSTANCE::reviewToReviewDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ReviewDTO getReviewById(@PathVariable("id") long id) {
        return ReviewMapper.INSTANCE.reviewToReviewDto(reviewService.getReviewById(id));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity getAllReviewsOfParticipation(
            @PathVariable("id") long id) {

        Review review = reviewService.getReviewById(id);
        reviewService.deleteReview(review);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
