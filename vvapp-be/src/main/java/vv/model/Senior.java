package vv.model;

import javax.persistence.*;
import javax.servlet.http.Part;
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
    private String authSchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private SeniorGroup group;

    @OneToMany(mappedBy = "senior")
    private Set<Participation> participations;

    @OneToMany(mappedBy = "senior")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "supervisor")
    private Set<Event> supervisor;

    private String role;

    public Long getSeniorId() {
        return seniorId;
    }

    public void setSeniorId(Long seniorId) {
        this.seniorId = seniorId;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAuthSchId() {
        return authSchId;
    }

    public void setAuthSchId(String authSchId) {
        this.authSchId = authSchId;
    }

    public SeniorGroup getGroup() {
        return group;
    }

    public void setGroup(SeniorGroup group) {
        this.group = group;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Event> getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Set<Event> supervisor) {
        this.supervisor = supervisor;
    }
}
