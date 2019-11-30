package vv.model;

public class AuthSchResponse {
    private String internal_id;
    private String displayName;
    private String sn;
    private String givenName;
    private String mail;
    private String mobile;
    private String[] bemunitscope;

    public String[] getBemunitscope() {
        return bemunitscope;
    }

    public void setBemunitscope(String[] bemunitscope) {
        this.bemunitscope = bemunitscope;
    }

    public String getInternal_id() {
        return internal_id;
    }

    public void setInternal_id(String internal_id) {
        this.internal_id = internal_id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
