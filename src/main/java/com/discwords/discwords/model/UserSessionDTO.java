package com.discwords.discwords.model;

public class UserSessionDTO {
    private Profile profile;
    private String token;

    public UserSessionDTO(){}
    public UserSessionDTO( Profile profile, String token) {
        this.profile = profile;
        this.token = token;
    }

    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public String getToken() {
        return token;
    }
    public void setToken(UserSession userSession) {
        this.token = token;
    }
}
