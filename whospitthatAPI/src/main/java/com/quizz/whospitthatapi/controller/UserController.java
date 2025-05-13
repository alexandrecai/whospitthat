package com.quizz.whospitthatapi.controller;

import com.quizz.whospitthatapi.dto.UserDto;
import com.quizz.whospitthatapi.entity.User;
import com.quizz.whospitthatapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDto user){
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        if(userService.getALlUsers().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(userService.getALlUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        if(userService.getUserById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        Optional<User> user = userService.getUserById(id);

        if(user.isPresent()){
            userService.deleteUser(user.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
