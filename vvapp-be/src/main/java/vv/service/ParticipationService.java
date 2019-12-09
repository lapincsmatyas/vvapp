package vv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.helper.notification.EmailNotificationSender;
import vv.model.*;
import vv.repository.ParticipationRepository;
import vv.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    EmailNotificationSender emailNotificationSender;

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

    public Review createReviewToParticipation(Participation participation, Senior senior, String text){
        if(participation == null)
            return null;
        if(senior == null){
            return null;
        }

        Review review = new Review();
        review.setSenior(senior);
        review.setParticipation(participation);
        review.setText(text);
        review.setDate(new Date());

        List<Senior> adminsOfGroup = seniorService.getAdminsOfGroup(participation.getSenior().getGroup());
        String[] to = adminsOfGroup.stream().map(admin -> admin.getEmail()).collect(Collectors.toList()).toArray(new String[0]);
        emailNotificationSender.sendEmailOfReview(to, review);

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
