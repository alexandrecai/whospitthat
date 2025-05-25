package com.quizz.whospitthatapi.service;

import com.quizz.whospitthatapi.dto.SignUpUser;
import com.quizz.whospitthatapi.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDetailsService userDetailService();
    List<User> getALlUsers();
    Optional<User> getUserById(Long id);
    User updateUser(User user);
    void deleteUser(User user);
}
