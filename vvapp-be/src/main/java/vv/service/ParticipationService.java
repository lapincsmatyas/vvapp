package vv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.model.*;
import vv.repository.ParticipationRepository;
import vv.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipationService {

    @Autowired
    EventService eventService;

    @Autowired
    SeniorService seniorService;

    @Autowired
    EventRoleService eventRoleService;

    @Autowired
    ParticipationRepository participationRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public List<Participation> getAllParticipations(){
        return participationRepository.findAll();
    }

    public Participation createParticipation(long eventId, long seniorId, long eventRoleId) {
        Event event = eventService.getEventById(eventId);
        Senior senior = seniorService.getSeniorById(seniorId);
        EventRole eventRole = eventRoleService.getEventRoleById(eventRoleId);

        Participation participation = new Participation();
        participation.setEvent(event);
        participation.setSenior(senior);
        participation.setEventRole(eventRole);

        return participationRepository.save(participation);
    }

    public Participation getParticipationById(long id) {
        return participationRepository.findById(id).orElse(null);
    }

    public Review createReviewToParticipation(long participationId, long seniorId, String text){
        Participation participation = participationRepository.findById(participationId).orElse(null);
        if(participation == null)
            return null;

        Senior senior = seniorService.getSeniorById(seniorId);
        if(senior == null){
            return null;
        }

        Review review = new Review();
        review.setSenior(senior);
        review.setParticipation(participation);
        review.setText(text);
        reviewRepository.save(review);
        return review;
    }

    public List<Review> getAllReviewsOfParticipation(long id) {
        Participation participation = participationRepository.findById(id).orElse(null);
        if(participation == null)
            return null;
        return new ArrayList<>(participation.getReviews());
    }
}