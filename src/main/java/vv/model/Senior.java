package vv.model;

import javax.persistence.*;

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
}
