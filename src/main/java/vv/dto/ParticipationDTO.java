package vv.dto;

public class ParticipationDTO {
    private Long participationId;
    private EventDTO event;
    private EventRoleDTO eventRole;

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
}
