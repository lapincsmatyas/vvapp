package vv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.model.Event;
import vv.model.EventRole;
import vv.model.Participation;
import vv.model.Senior;
import vv.repository.ParticipationRepository;

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
}
