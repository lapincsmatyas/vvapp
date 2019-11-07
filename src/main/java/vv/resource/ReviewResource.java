package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/review")
public class ReviewResource {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ParticipationService participationService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ReviewDTO> getAllReviews(){
        List<Review> seniors = reviewService.getAllReviews();
        return seniors.stream().map(ReviewMapper.INSTANCE::reviewToReviewDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ReviewDTO getReviewById(@PathVariable("id") long id) {
        return ReviewMapper.INSTANCE.reviewToReviewDto(reviewService.getReviewById(id));
    }
}
