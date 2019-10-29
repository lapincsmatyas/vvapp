package vv.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "event_id_seq", sequenceName = "event_id_seq")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_seq")
    @Column(name = "event_id")
    private Long eventId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventTypeId")
    private EventType eventType;

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
}
