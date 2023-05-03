package com.br.login.repository;

import com.br.login.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username) throws UsernameNotFoundException;

    void deleteUserByUsername(String username) throws UsernameNotFoundException;

}
