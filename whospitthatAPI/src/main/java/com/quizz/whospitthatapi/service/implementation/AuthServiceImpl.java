package com.quizz.whospitthatapi.service.implementation;

import com.quizz.whospitthatapi.dto.JWTAuthenticationResponse;
import com.quizz.whospitthatapi.dto.RefreshToken;
import com.quizz.whospitthatapi.dto.SignInUser;
import com.quizz.whospitthatapi.dto.SignUpUser;
import com.quizz.whospitthatapi.entity.Role;
import com.quizz.whospitthatapi.entity.User;
import com.quizz.whospitthatapi.exception.UserAlreadyExistException;
import com.quizz.whospitthatapi.exception.UserNotFoundException;
import com.quizz.whospitthatapi.repository.UserRepository;
import com.quizz.whospitthatapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTServiceImpl jwtService;

    public User signUpUser(SignUpUser signUpUser) throws UserAlreadyExistException {

        if (userRepository.findByEmail(signUpUser.getEmail()).isPresent()){
            throw new UserAlreadyExistException();
        }

        User user = new User();

        user.setName(signUpUser.getName());
        user.setEmail(signUpUser.getEmail());
        user.setPassword(passwordEncoder.encode(signUpUser.getPassword()));
        user.setHighscore(0);
        user.setPictureUrl(signUpUser.getPictureUrl());
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public JWTAuthenticationResponse signInUser(SignInUser signInUser) throws UserNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInUser.getEmail(), signInUser.getPassword()));

        var user = userRepository.findByEmail(signInUser.getEmail()).orElseThrow(UserNotFoundException::new);
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JWTAuthenticationResponse refreshToken(RefreshToken refreshToken){
        String userEmail = jwtService.extractUsername(refreshToken.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshToken.getToken(),user)){
            var jwt = jwtService.generateToken(user);

            JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    public String getUserByToken(String email) throws UserNotFoundException {

        if (userRepository.findByEmail(email).isPresent()){
            return userRepository.findByEmail(email).get().getId().toString();
        }
        throw new UserNotFoundException();
    }
}
