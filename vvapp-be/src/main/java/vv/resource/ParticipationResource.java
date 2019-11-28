package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
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
import vv.service.ReviewService;
import vv.service.SeniorService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/participation")
public class ParticipationResource {

    @Autowired
    ParticipationService participationService;

    @GetMapping
    public List<ParticipationDTO> getAllParticipations() {
        List<Participation> events = participationService.getAllParticipations();
        return events.stream().map(ParticipationMapper.INSTANCE::participationToParticipationDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ParticipationDetailDTO getParticipationById(@PathVariable("id") long id) {
        return ParticipationMapper.INSTANCE.participationToParticipationDetailDto(participationService.getParticipationById(id));
    }

    @PostMapping(value = "/{id}/review")
    public ReviewDTO addReviewToParticipation(
            @PathVariable("id") long participationId,
            @RequestParam("seniorId") long seniorId,
            @RequestBody String text) {
        Review review = participationService.createReviewToParticipation(participationId, seniorId, text);
        return ReviewMapper.INSTANCE.reviewToReviewDto(review);
    }

    @GetMapping(value = "/{id}/review")
    public List<ReviewDTO> getAllReviewsOfParticipation(
            @PathVariable("id") long id) {
        List<Review> reviews = participationService.getAllReviewsOfParticipation(id);
        return reviews.stream().map(ReviewMapper.INSTANCE::reviewToReviewDto).collect(Collectors.toList());
    }


}
