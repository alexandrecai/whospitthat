package com.quizz.whospitthatapi.service.implementation;

import com.quizz.whospitthatapi.dto.UserDto;
import com.quizz.whospitthatapi.entity.User;
import com.quizz.whospitthatapi.repository.UserRepository;
import com.quizz.whospitthatapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDto user) {
        User newUser = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .highscore(0)
                .password(user.getPassword())
                .pictureUrl(user.getPictureUrl())
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public List<User> getALlUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
