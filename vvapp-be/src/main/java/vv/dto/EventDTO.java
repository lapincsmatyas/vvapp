package vv.dto;

public class EventDTO {
    private Long eventId;
    private String name;
    private EventTypeDTO eventType;
    private SeniorDTO supervisor;

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

    public SeniorDTO getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(SeniorDTO supervisor) {
        this.supervisor = supervisor;
    }
}
