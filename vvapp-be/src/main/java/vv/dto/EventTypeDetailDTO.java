package vv.dto;

import java.util.List;

public class EventTypeDetailDTO {
    private String name;
    private Long eventTypeId;
    private List<EventDTO> events;
    private List<EventRoleDTO> eventRoles;

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

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }

    public List<EventRoleDTO> getEventRoles() {
        return eventRoles;
    }

    public void setEventRoles(List<EventRoleDTO> eventRoles) {
        this.eventRoles = eventRoles;
    }
}
