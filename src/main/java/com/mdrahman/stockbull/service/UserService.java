package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.dto.UserRegistrationDto;
import com.mdrahman.stockbull.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(UserRegistrationDto registration);
    void deleteUser(Long id);
    List<User> searchByEmail(String email);
    List<User> searchByFirstName(String firstName);
}
