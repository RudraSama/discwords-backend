package com.discwords.discwords.model;

public class UserSessionDTO {
    private UserSession userSession;
    private User user;

    public UserSessionDTO(){}
    public UserSessionDTO(UserSession userSession, User user) {
        this.userSession = userSession;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
