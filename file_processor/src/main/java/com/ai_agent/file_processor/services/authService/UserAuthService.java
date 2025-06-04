package com.ai_agent.file_processor.services.authService;

import com.ai_agent.file_processor.exceptions.authExceptions.IncorrectPasswordException;
import com.ai_agent.file_processor.exceptions.authExceptions.UserAlreadyExistsException;
import com.ai_agent.file_processor.exceptions.authExceptions.UserNotFoundException;
import com.ai_agent.file_processor.models.IsDeleted;
import com.ai_agent.file_processor.models.authModels.Session;
import com.ai_agent.file_processor.models.authModels.User;
import com.ai_agent.file_processor.models.authModels.UserSessionStatus;
import com.ai_agent.file_processor.repos.SessionRepository;
import com.ai_agent.file_processor.repos.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserAuthService implements AuthService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SecretKey secretKey;

    // signup code implementation
    @Override
    public User signUp(String enail, String password) throws UserAlreadyExistsException{

        Optional<User> userOptional = userRepository.findByEmail(enail);
        if(userOptional.isPresent()) {
            if(userOptional.get().getEmail().equals(enail)) {
                throw new UserAlreadyExistsException("Email already exists");
            }
        }

        User user = new User();
        user.setEmail(enail);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);

        return user;
    }


    // Login code implementation
    @Override
    public Pair<User, String> login(String email, String password) throws UserNotFoundException, IncorrectPasswordException {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty() || !userOptional.get().getEmail().equals(email)) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();

        String userPassword = user.getPassword();

        // BcryptPasswordeEncoder is the class provided by Spring security for
        // storing the passwords in encoded format which will not turn to the same string
        // as it was
        if(!bCryptPasswordEncoder.matches(password, userPassword)) {
            throw new IncorrectPasswordException("password galat he ");
        }

        // JWT token generation implementation, whenever a new login happen, a session
        // will be created with a jwt token


        // have been using the map for creating the claims which is actually the payload
        // of the token which will contains the kinda required info like the user id, issuer,
        // token issued time , expiry of token etc.
        // NOTE : claims use map in the internal implementation so we are using the map that
        // will work with more compatibility
        Map<String, Object> claims = new HashMap<>();

        claims.put("userEmail", user.getEmail());
        claims.put("userId", user.getId());
        long nowInMillis = System.currentTimeMillis();
        claims.put("iat", nowInMillis);
        claims.put("exp", nowInMillis + 18000000);
        claims.put("iss", "shoebUserService");

        // MacAlgorith is very popular and common algorithm
        // it's a cryptographic algorithm often use hs256
        // MAC = Message Authentication Code
        // need to read HMAC and SHA256

        //MacAlgorithm algorithm = Jwts.SIG.HS256;
        //SecretKey secret = algorithm.key().build();


        // commented the above two lines of generating the secret key because we will need the same
        // secret key at the time of validation and there will be the different object that's
        // why we will create a bean of the secret key in config class in order to make it singleton


        // token genneration using Jwts.Builder which has claims that is payload
        // sign with which is taking the secret and compact method is finally
        // creating the token
        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        // now we will need to code for storing the token in db in order to use the token
        // in future for validation which will further use to control or manage the user
        // sessions
        Session session = new Session();
        session.setUser(user);
        session.setToken(token);
        session.setStatus(UserSessionStatus.ACTIVE);
        sessionRepository.save(session);

        Pair pair = new Pair(user, token);

        return pair;
    }


    // Token validatoin code -> it will be self validating the token
    @Override
    public boolean validateToken(String token, long userId) {

        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUserId(token, userId);

        if(sessionOptional.isEmpty()) {
            return false;
        }

        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        Long expiry = (Long) claims.get("exp");

        if(expiry < System.currentTimeMillis()) {

            Session session = sessionOptional.get();
            session.setStatus(UserSessionStatus.EXPIRED);
            session.setIsDeleted(IsDeleted.YES);
            sessionRepository.save(session);

            return false;
        }

        return true;
    }
}
