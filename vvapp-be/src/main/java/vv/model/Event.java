package vv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@SequenceGenerator(name = "event_event_id_seq", sequenceName = "event_event_id_seq", initialValue = 1, allocationSize = 1)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_event_id_seq")
    @Column(name = "event_id")
    private Long eventId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    @OneToMany(mappedBy = "event")
    private Set<Participation> participations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id")
    private Senior supervisor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }


    public Senior getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Senior supervisor) {
        this.supervisor = supervisor;
    }
}
