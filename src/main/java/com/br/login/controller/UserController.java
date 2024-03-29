package com.br.login.controller;

import com.br.login.domain.User;
import com.br.login.dto.UserCreate;
import com.br.login.dto.UserView;
import com.br.login.mapper.UserMapper;
import com.br.login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/users", produces = "application/json")
public class UserController {

    private final UserService service;
    private final UserMapper userMapper;

    public UserController(UserService service, UserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserView> create(@Valid @RequestBody UserCreate userCreate) {

        User saved = this.service.save(userCreate);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getUsername())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, location.toString()).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<User>> find(@RequestParam(defaultValue = "0") Integer pageNumber,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(defaultValue = "username") String sortBy) {
        Page<User> userPage = this.service.findAll(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(userPage);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserView> findByUsername(@PathVariable String username) {
        User user = this.service.findByUsername(username);
        return ResponseEntity.ok(this.userMapper.mapToUserView(user));
    }

    @PutMapping
    public ResponseEntity<UserView> update(@Valid UserCreate userCreate) {
        User user = this.service.findByUsername(userCreate.getUsername());
        user = this.userMapper.update(user, userCreate);
        return ResponseEntity.ok(this.userMapper.mapToUserView(user));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        this.service.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

}
