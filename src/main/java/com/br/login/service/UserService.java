package com.br.login.service;

import com.br.login.domain.User;
import com.br.login.dto.UserCreate;
import com.br.login.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        return user.get();
    }

    public User save(UserCreate userCreate) {
        User user = new User(userCreate.getUsername(), userCreate.getEmail(), passwordEncoder.encode(userCreate.getPassword()));
        return this.userRepository.save(user);
    }

    public void deleteUserByUsername(String username) {
        this.userRepository.deleteByUsername(username);
    }

}
