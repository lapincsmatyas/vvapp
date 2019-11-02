package vv.dto;

import java.util.List;

public class SeniorDTO {
    private Long seniorId;
    private String name;
    private String email;
    private List<ParticipationDTO> participations;

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
}