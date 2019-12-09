package vv.dto;

import vv.model.Review;

import java.util.List;

public class ParticipationDetailDTO {
    private Long participationId;
    private EventDTO event;
    private EventRoleDTO eventRole;
    private SeniorDTO senior;
    private List<ReviewDTO> reviews;
    private boolean state;

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

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
