package com.ai_agent.file_processor.models.authModels;

import com.ai_agent.file_processor.models.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Session extends BaseModel {

    @ManyToOne
    private User user;

    private String token;

    private UserSessionStatus status;

    public UserSessionStatus getStatus() {
        return status;
    }

    public void setStatus(UserSessionStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
