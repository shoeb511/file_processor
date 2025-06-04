package com.ai_agent.file_processor.repos;

import com.ai_agent.file_processor.models.authModels.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    public Optional<Session> findByTokenAndUserId(String token, Long userId);
}
