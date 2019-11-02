package vv.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "participation_participation_id_seq", sequenceName = "participation_participation_id_seq")
@SequenceGenerator(name = "participation_participation_id_seq", sequenceName = "participation_participation_id_seq", initialValue = 1, allocationSize = 1)
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participation_participation_id_seq")
    private Long participationId;

    @ManyToOne
    @JoinColumn(name = "senior_id")
    private Senior senior;

    @ManyToOne
    @JoinColumn(name = "event_role_id")
    private EventRole eventRole;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Senior getSenior() {
        return senior;
    }

    public void setSenior(Senior senior) {
        this.senior = senior;
    }

    public Long getParticipationId() {
        return participationId;
    }

    public void setParticipationId(Long participationId) {
        this.participationId = participationId;
    }

    public EventRole getEventRole() {
        return eventRole;
    }

    public void setEventRole(EventRole eventRole) {
        this.eventRole = eventRole;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
