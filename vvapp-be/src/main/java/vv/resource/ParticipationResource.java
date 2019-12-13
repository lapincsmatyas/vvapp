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

import javax.servlet.http.Part;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://152.66.178.92:3000", allowCredentials = "true")
@RestController
@RequestMapping("/participation")
public class ParticipationResource {

    @Autowired
    ParticipationService participationService;

    @Autowired
    SeniorService seniorService;

    @Autowired
    AuthSchTokenResponse authSchTokenResponse;

    @Autowired
    AuthSchService authSchService;
    
    @Autowired
    ParticipationMapper participationMapper;
    
    @Autowired
    ReviewMapper reviewMapper;

    @GetMapping
    public List<ParticipationDTO> getAllParticipations() {
        List<Participation> events = participationService.getAllParticipations();
        return events.stream().map(participationMapper::participationToParticipationDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ParticipationDetailDTO getParticipationById(@PathVariable("id") long id) {
        return participationMapper.participationToParticipationDetailDto(participationService.getParticipationById(id));
    }

    @PostMapping(value = "/{id}/review")
    public ResponseEntity addReviewToParticipation(
            @PathVariable("id") long participationId,
            @RequestBody String text) {

        Senior actSenior = seniorService.getSeniorByAuthSchId(authSchService.getData().getInternal_id());
        Participation participation = participationService.getParticipationById(participationId);

        if(!(actSenior.getUserRole().getName().equals("VÁRÚR")) && !participation.getEvent().getSupervisor().getSeniorId().equals(actSenior.getSeniorId())){
            return new ResponseEntity<>("Only event supervisors or ADMINS can add reviews!", HttpStatus.UNAUTHORIZED);
        }

        Review review = participationService.createReviewToParticipation(participation, actSenior, text);
        return new ResponseEntity<>(reviewMapper.reviewToReviewDto(review), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/review")
    public List<ReviewDTO> getAllReviewsOfParticipation(
            @PathVariable("id") long id) {
        List<Review> reviews = participationService.getAllReviewsOfParticipation(id);
        return reviews.stream().map(reviewMapper::reviewToReviewDto).collect(Collectors.toList());
    }

    @PostMapping(value = "/{id}/accept")
    public ResponseEntity addReviewToParticipation(
            @PathVariable("id") long participationId,
            @RequestBody boolean newValue) {
        Senior actSenior = seniorService.getSeniorByAuthSchId(authSchService.getData().getInternal_id());
        Participation participation = participationService.getParticipationById(participationId);

        if(!(actSenior.getUserRole().getName().equals("VÁRÚR")) && !participation.getEvent().getSupervisor().getSeniorId().equals(actSenior.getSeniorId())){
            return new ResponseEntity<>("Only event supervisors or ADMINS can add reviews!", HttpStatus.UNAUTHORIZED);
        }
        if(newValue) {
            participation.setState(newValue);
            participationService.save(participation);
            return new ResponseEntity<>(participationMapper.participationToParticipationDto(participation), HttpStatus.OK);
        }
        else {
            participationService.delete(participation);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }

}
