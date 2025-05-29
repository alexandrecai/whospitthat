package com.quizz.whospitthatapi.controller;

import com.quizz.whospitthatapi.dto.JWTAuthenticationResponse;
import com.quizz.whospitthatapi.dto.RefreshToken;
import com.quizz.whospitthatapi.dto.SignInUser;
import com.quizz.whospitthatapi.dto.SignUpUser;
import com.quizz.whospitthatapi.entity.User;
import com.quizz.whospitthatapi.exception.UserAlreadyExistException;
import com.quizz.whospitthatapi.exception.UserNotFoundException;
import com.quizz.whospitthatapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/signup")
    private ResponseEntity<User> signUpUser(@RequestBody SignUpUser signUpUser){
        User user;
        try {
            user = authService.signUpUser(signUpUser);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.created(URI.create("/api/user/"+user.getId())).body(user);
    }

    @PostMapping("/signin")
    private ResponseEntity<JWTAuthenticationResponse> signInUser(@RequestBody SignInUser signInUser){
        try {
            return ResponseEntity.ok(authService.signInUser(signInUser));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/refresh")
    private ResponseEntity<JWTAuthenticationResponse> refreshToken(@RequestBody RefreshToken refreshToken){

        return ResponseEntity.ok(authService.refreshToken(refreshToken));

    }
}
