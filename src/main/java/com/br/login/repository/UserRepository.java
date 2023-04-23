package com.br.login.repository;

import com.br.login.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username) throws UsernameNotFoundException;

    void deleteUserByUsername(String username) throws UsernameNotFoundException;

}
