package vv.dto;

public class EventRoleDTO {
    private long eventRoleId;
    private String name;
    private EventTypeDTO eventType;

    public long getEventRoleId() {
        return eventRoleId;
    }

    public void setEventRoleId(long eventRoleId) {
        this.eventRoleId = eventRoleId;
    }

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
}
