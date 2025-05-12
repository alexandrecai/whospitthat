package com.quizz.whospitthatapi.service;

import com.quizz.whospitthatapi.dto.UserDto;
import com.quizz.whospitthatapi.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto user);
    List<User> getALlUsers();
    User getUserById(Long id);
}
