package com.ai_agent.file_processor.services.authService;

import com.ai_agent.file_processor.exceptions.authExceptions.IncorrectPasswordException;
import com.ai_agent.file_processor.exceptions.authExceptions.UserAlreadyExistsException;
import com.ai_agent.file_processor.exceptions.authExceptions.UserNotFoundException;
import com.ai_agent.file_processor.models.authModels.User;
import org.antlr.v4.runtime.misc.Pair;

public interface AuthService {

    public User signUp(String enail, String password) throws UserAlreadyExistsException;

    public Pair<User, String> login(String email, String password) throws UserNotFoundException, IncorrectPasswordException;

    public boolean validateToken(String token , long userId);
}
