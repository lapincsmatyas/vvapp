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
import vv.service.SeniorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/participation")
public class ParticipationResource {

    @Autowired
    ParticipationService participationService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ParticipationDTO> getAllParticipations() {
        List<Participation> events = participationService.getAllParticipations();
        return events.stream().map(ParticipationMapper.INSTANCE::participationToParticipationDto).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ParticipationDetailDTO getParticipationById(@PathVariable("id") long id) {
        return ParticipationMapper.INSTANCE.participationToParticipationDetailDto(participationService.getParticipationById(id));
    }

    @RequestMapping(value = "/{id}/review", method = RequestMethod.POST)
    public ReviewDTO addReviewToParticipation(
            @PathVariable("id") long participationId,
            @RequestParam("seniorId") long seniorId,
            @RequestBody ReviewDTO reviewDTO){
        Review review = ReviewMapper.INSTANCE.reviewDtoToReview(reviewDTO);
        review = participationService.createReviewToParticipation(participationId, seniorId, review.getText());
        return ReviewMapper.INSTANCE.reviewToReviewDto(review);
    }
}
