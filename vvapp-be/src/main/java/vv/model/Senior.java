package vv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@SequenceGenerator(name = "senior_senior_id_seq", sequenceName = "senior_senior_id_seq")
@SequenceGenerator(name = "senior_senior_id_seq", sequenceName = "senior_senior_id_seq", initialValue = 1, allocationSize = 1)
public class Senior {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "senior_senior_id_seq")
    @Column(name = "senior_id")
    private Long seniorId;

    private String name;
    private String email;
    private String mobile;

    @OneToMany(mappedBy = "senior")
    private Set<Participation> participations;

    @OneToMany(mappedBy = "senior")
    private Set<Review> reviews;

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

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
