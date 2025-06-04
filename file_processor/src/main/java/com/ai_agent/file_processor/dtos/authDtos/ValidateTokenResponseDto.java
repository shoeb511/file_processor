package com.ai_agent.file_processor.dtos.authDtos;

import com.ai_agent.file_processor.models.authModels.UserSessionStatus;

public class ValidateTokenResponseDto {
    Long userId;
    UserSessionStatus userSessionStatus;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserSessionStatus getUserSessionStatus() {
        return userSessionStatus;
    }

    public void setUserSessionStatus(UserSessionStatus userSessionStatus) {
        this.userSessionStatus = userSessionStatus;
    }
}
