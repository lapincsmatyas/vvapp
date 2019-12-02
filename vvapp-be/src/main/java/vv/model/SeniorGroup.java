package vv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class SeniorGroup {
    @Id
    @Column(name = "group_id")
    private Long groupId;

    private String name;

    @OneToMany(mappedBy = "group")
    private Set<Senior> seniors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Senior> getSeniors() {
        return seniors;
    }

    public void setSeniors(Set<Senior> seniors) {
        this.seniors = seniors;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
