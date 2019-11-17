package vv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@SequenceGenerator(name = "event_role_event_role_id", sequenceName = "event_role_event_role_id")
@SequenceGenerator(name = "event_role_event_role_id", sequenceName = "event_role_event_role_id", initialValue = 1, allocationSize = 1)
public class EventRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_role_event_role_id")
    @Column(name = "event_role_id")
    private Long eventRoleId;
    private String name;

    @OneToMany(mappedBy = "eventRole")
    private Set<Participation> participations;

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
}
