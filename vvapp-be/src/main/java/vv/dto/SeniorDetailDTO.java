package vv.dto;

import java.util.List;

public class SeniorDetailDTO {
    private Long seniorId;
    private String name;
    private String email;
    private List<ParticipationDTO> participations;
    private SeniorGroupDTO group;
    private String role;
    private String mobile;
    private List<ParticipationDTO> events;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSeniorId() {
        return seniorId;
    }

    public void setSeniorId(Long seniorId) {
        this.seniorId = seniorId;
    }

    public List<ParticipationDTO> getParticipations() {
        return participations;
    }

    public void setParticipations(List<ParticipationDTO> participations) {
        this.participations = participations;
    }

    public SeniorGroupDTO getGroup() {
        return group;
    }

    public void setGroup(SeniorGroupDTO group) {
        this.group = group;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ParticipationDTO> getEvents() {
        return events;
    }

    public void setEvents(List<ParticipationDTO> events) {
        this.events = events;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}