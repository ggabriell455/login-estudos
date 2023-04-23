package com.br.login.controller;

import com.br.login.domain.User;
import com.br.login.dto.UserCreate;
import com.br.login.dto.UserView;
import com.br.login.mapper.UserMapper;
import com.br.login.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserView> create(@Valid @RequestBody UserCreate userCreate) {
        User user = this.userMapper.mapToUser(userCreate);
        User saved = this.userRepository.save(user);
        return ResponseEntity.ok(this.userMapper.mapToUserView(saved));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserView> findByUsername(@PathVariable String username) {
        User user = this.userRepository.findByUsername(username);
        return ResponseEntity.ok(this.userMapper.mapToUserView(user));
    }

    @PutMapping
    public ResponseEntity<UserView> update(@Valid UserCreate userCreate) {
        User user = this.userRepository.findByUsername(userCreate.getUsername());
        user = this.userMapper.update(user, userCreate);
        return ResponseEntity.ok(this.userMapper.mapToUserView(user));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        this.userRepository.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

}
