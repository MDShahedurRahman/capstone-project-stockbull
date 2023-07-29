package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.dto.UserRegistrationDto;
import com.mdrahman.stockbull.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    // Find a user by their email address.
    User findByEmail(String email);

    // Save a new user registration to the database.
    User save(UserRegistrationDto registration);

    // Delete a user by their unique ID.
    void deleteUser(Long id);

    // Search for users by their email address, returning a list of matching users.
    List<User> searchByEmail(String email);

    // Search for users by their first name, returning a list of matching users.
    List<User> searchByFirstName(String firstName);

}
