package vv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @Column(name = "user_role_id")
    private Long userRoleId;

    private String name;

    @OneToMany(mappedBy = "userRole")
    private Set<Senior> seniors;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeniors(Set<Senior> seniors){
        this.seniors = seniors;
    }

    public Set<Senior> getSeniors(){
        return this.seniors;
    }
}
