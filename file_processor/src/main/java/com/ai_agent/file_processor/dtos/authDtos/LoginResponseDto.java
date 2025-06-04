package com.ai_agent.file_processor.dtos.authDtos;

import com.ai_agent.file_processor.models.authModels.UserSessionStatus;

public class LoginResponseDto {
    private String email;
    private Long userId;
    private String message;
    private UserSessionStatus status;

    public UserSessionStatus getStatus() {
        return status;
    }

    public void setStatus(UserSessionStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
