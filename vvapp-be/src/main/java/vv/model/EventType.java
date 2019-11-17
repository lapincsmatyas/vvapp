package vv.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@SequenceGenerator(name = "event_type_event_type_id_seq", sequenceName = "event_type_event_type_id_seq")
@SequenceGenerator(name = "event_type_event_type_id_seq", sequenceName = "event_type_event_type_id_seq", initialValue = 1, allocationSize = 1)
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_type_event_type_id_seq")
    @Column(name = "event_type_id")
    private Long eventTypeId;

    private String name;

    @OneToMany(mappedBy = "eventType")
    @JsonBackReference
    private Set<Event> events;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Long eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}