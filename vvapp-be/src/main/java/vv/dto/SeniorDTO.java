package vv.dto;

public class SeniorDTO {
    private Long seniorId;
    private String name;
    private String email;
    private String mobile;
    private SeniorGroupDTO group;
    private UserRoleDTO userRole;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public SeniorGroupDTO getGroup() {
        return group;
    }

    public void setGroup(SeniorGroupDTO group) {
        this.group = group;
    }

    public UserRoleDTO getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleDTO userRole) {
        this.userRole = userRole;
    }
}