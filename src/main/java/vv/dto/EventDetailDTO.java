package vv.dto;

import vv.model.Senior;

import java.util.List;

public class EventDetailDTO {
    private Long eventId;
    private String name;
    private EventTypeDTO eventType;
    private List<ParticipationDTO> participations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventTypeDTO getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeDTO eventType) {
        this.eventType = eventType;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<ParticipationDTO> getParticipations() {
        return participations;
    }

    public void setParticipations(List<ParticipationDTO> participations) {
        this.participations = participations;
    }
}
