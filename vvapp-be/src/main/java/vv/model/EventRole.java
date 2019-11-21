package vv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@SequenceGenerator(name = "event_role_event_role_id_seq", sequenceName = "event_role_event_role_id_seq")
@SequenceGenerator(name = "event_role_event_role_id_seq", sequenceName = "event_role_event_role_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "event_role")
public class EventRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_role_event_role_id_seq")
    @Column(name = "event_role_id")
    private Long eventRoleId;
    private String name;

    @OneToMany(mappedBy = "eventRole")
    private Set<Participation> participations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEventRoleId() {
        return eventRoleId;
    }

    public void setEventRoleId(Long eventRoleId) {
        this.eventRoleId = eventRoleId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
