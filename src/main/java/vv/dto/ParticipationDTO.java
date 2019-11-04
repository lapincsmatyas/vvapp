package vv.dto;

import vv.model.Senior;

public class ParticipationDTO {
    private Long participationId;
    private EventDTO event;
    private EventRoleDTO eventRole;
    private SeniorDTO senior;

    public Long getParticipationId() {
        return participationId;
    }

    public void setParticipationId(Long participationId) {
        this.participationId = participationId;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public EventRoleDTO getEventRole() {
        return eventRole;
    }

    public void setEventRole(EventRoleDTO eventRole) {
        this.eventRole = eventRole;
    }

    public SeniorDTO getSenior() {
        return senior;
    }

    public void setSenior(SeniorDTO senior) {
        this.senior = senior;
    }
}
