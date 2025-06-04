package com.ai_agent.file_processor.controllers;

import com.ai_agent.file_processor.dtos.authDtos.*;
import com.ai_agent.file_processor.exceptions.authExceptions.IncorrectPasswordException;
import com.ai_agent.file_processor.exceptions.authExceptions.UserAlreadyExistsException;
import com.ai_agent.file_processor.exceptions.authExceptions.UserNotFoundException;
import com.ai_agent.file_processor.models.authModels.User;
import com.ai_agent.file_processor.models.authModels.UserSessionStatus;
import com.ai_agent.file_processor.services.authService.AuthService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/newUser")
    public SignupResponseDto userSignUp(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException {

        User user = authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());

        SignupResponseDto userDto = new SignupResponseDto();

        if(user == null){
            userDto.setMessage("user already exists");
        }
        else{
            userDto.setEmail(user.getEmail());
            userDto.setMessage("signup successful");
        }


        return userDto;
    }


    @PostMapping(value = "user")
    public ResponseEntity<LoginResponseDto> userLogin(@RequestBody LoginRequestDto loginDto) {

        try {
            Pair pair = authService.login(loginDto.getEmail(), loginDto.getPassword());

            User user = (User) pair.a;
            String token = (String) pair.b;

            LoginResponseDto loginRespnseDto = new LoginResponseDto();
            loginRespnseDto.setEmail(user.getEmail());
            loginRespnseDto.setUserId(user.getId());
            loginRespnseDto.setMessage("login successful");
            loginRespnseDto.setStatus(UserSessionStatus.ACTIVE);

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.SET_COOKIE, token);

            ResponseEntity responseEntity = new ResponseEntity(loginRespnseDto, headers, HttpStatusCode.valueOf(201));

            return responseEntity;
        }

        catch (UserNotFoundException e) {
            LoginResponseDto respnseDto = new LoginResponseDto();
            respnseDto.setMessage("user not found");
            return new ResponseEntity<>(respnseDto, HttpStatus.NOT_FOUND);
        }

        catch ( IncorrectPasswordException e) {
            LoginResponseDto respnseDto = new LoginResponseDto();
            respnseDto.setMessage("incorrect password entered");
            return new ResponseEntity<>(respnseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "validity")
    public ResponseEntity<ValidateTokenResponseDto> validateToken(@RequestBody ValidateTokenRequestDto validateTokenDto) {

        String token = validateTokenDto.getToken();
        long userId = validateTokenDto.getUserId();

        boolean tokenStatus = authService.validateToken(token, userId);

        ValidateTokenResponseDto responseDto = new ValidateTokenResponseDto();

        if(tokenStatus){

            responseDto.setUserId(userId);
            responseDto.setUserSessionStatus(UserSessionStatus.ACTIVE);

            ResponseEntity responseEntity = new ResponseEntity(responseDto, HttpStatus.OK);

            return responseEntity;
        }

        responseDto.setUserId(userId);
        responseDto.setUserSessionStatus(UserSessionStatus.EXPIRED);

        ResponseEntity responseEntity = new ResponseEntity(responseDto, HttpStatus.UNAUTHORIZED);

        return responseEntity;
    }
}
