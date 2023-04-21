package com.br.login.login.controller;

import com.br.login.login.domain.User;
import com.br.login.login.dto.UserCreate;
import com.br.login.login.dto.UserView;
import com.br.login.login.mapper.UserMapper;
import com.br.login.login.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserControlelr {

    private UserRepository userRepository;
    private UserMapper userMapper;


    public UserControlelr(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserView> create(@Valid UserCreate userCreate) {
        User user = this.userMapper.mapToUser(userCreate);
        User saved = this.userRepository.save(user);
        return ResponseEntity.ok(this.userMapper.mapToUserView(saved));
    }

    @GetMapping()
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Hello Admin";
    }

}
