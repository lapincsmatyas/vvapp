package vv.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.dto.*;
import vv.helper.mapper.EventMapper;
import vv.helper.mapper.ParticipationMapper;
import vv.helper.mapper.ReviewMapper;
import vv.model.*;
import vv.repository.EventRoleRepository;
import vv.service.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/participation")
public class ParticipationResource {

    @Autowired
    ParticipationService participationService;

    @Autowired
    SeniorService seniorService;

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
    public ResponseEntity addReviewToParticipation(
            @PathVariable("id") long participationId,
            @RequestBody String text) {
        /*
        Participation participation = participationService.getParticipationById(participationId);
        Review review = participationService.createReviewToParticipation(participation, actSenior, text);
        return new ResponseEntity<>(ReviewMapper.INSTANCE.reviewToReviewDto(review), HttpStatus.OK);

         */
        return null;
    }

    @GetMapping(value = "/{id}/review")
    public List<ReviewDTO> getAllReviewsOfParticipation(
            @PathVariable("id") long id) {
        List<Review> reviews = participationService.getAllReviewsOfParticipation(id);
        return reviews.stream().map(ReviewMapper.INSTANCE::reviewToReviewDto).collect(Collectors.toList());
    }


}
