package vv.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_type_id")
    private Long eventTypeId;
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "eventType"
    )
    private List<Event> events = new ArrayList<>();

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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
