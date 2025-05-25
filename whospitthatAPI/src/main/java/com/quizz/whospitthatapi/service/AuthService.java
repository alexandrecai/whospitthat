package com.quizz.whospitthatapi.service;

import com.quizz.whospitthatapi.dto.JWTAuthenticationResponse;
import com.quizz.whospitthatapi.dto.RefreshToken;
import com.quizz.whospitthatapi.dto.SignInUser;
import com.quizz.whospitthatapi.dto.SignUpUser;
import com.quizz.whospitthatapi.entity.User;
import com.quizz.whospitthatapi.exception.UserAlreadyExistException;
import com.quizz.whospitthatapi.exception.UserNotFoundException;

public interface AuthService {

    User signUpUser(SignUpUser signUpUser) throws UserAlreadyExistException;
    JWTAuthenticationResponse signInUser(SignInUser signInUser) throws UserNotFoundException;
    JWTAuthenticationResponse refreshToken(RefreshToken refreshToken);
    String getUserByToken(String email) throws UserNotFoundException;
}
