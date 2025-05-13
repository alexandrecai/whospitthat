package com.quizz.whospitthatapi.service;

import com.quizz.whospitthatapi.dto.UserDto;
import com.quizz.whospitthatapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(UserDto user);
    List<User> getALlUsers();
    Optional<User> getUserById(Long id);
    User updateUser(User user);
    void deleteUser(User user);
}
