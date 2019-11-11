package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.dto.*;
import vv.helper.mapper.EventMapper;
import vv.helper.mapper.ParticipationMapper;
import vv.helper.mapper.ReviewMapper;
import vv.model.Event;
import vv.model.Participation;
import vv.model.Review;
import vv.repository.EventRoleRepository;
import vv.service.EventService;
import vv.service.ParticipationService;
import vv.service.SeniorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/participation")
public class ParticipationResource {

    @Autowired
    ParticipationService participationService;

    @GetMapping
    public ResponseEntity<List<ParticipationDTO>> getAllParticipations() {
        List<Participation> events = participationService.getAllParticipations();
        return new ResponseEntity<>(events.stream().map(ParticipationMapper.INSTANCE::participationToParticipationDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParticipationDetailDTO> getParticipationById(@PathVariable("id") long id) {
        return new ResponseEntity<>(ParticipationMapper.INSTANCE.participationToParticipationDetailDto(participationService.getParticipationById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/review")
    public ResponseEntity<ReviewDTO> addReviewToParticipation(
            @PathVariable("id") long participationId,
            @RequestParam("seniorId") long seniorId,
            @RequestBody ReviewDTO reviewDTO) {
        Review review = ReviewMapper.INSTANCE.reviewDtoToReview(reviewDTO);
        review = participationService.createReviewToParticipation(participationId, seniorId, review.getText());
        return new ResponseEntity<>(ReviewMapper.INSTANCE.reviewToReviewDto(review), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/review")
    public ResponseEntity<List<ReviewDTO>> getAllReviewsOfParticipation(
            @PathVariable("id") long id) {
        List<Review> reviews = participationService.getAllReviewsOfParticipation(id);
        return new ResponseEntity<>(reviews.stream().map(ReviewMapper.INSTANCE::reviewToReviewDto).collect(Collectors.toList()), HttpStatus.OK);
    }
}
