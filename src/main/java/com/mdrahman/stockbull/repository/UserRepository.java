package com.mdrahman.stockbull.repository;

import com.mdrahman.stockbull.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by their email
    User findByEmail(String email);

    // Find a list of users whose email contains the given string (case-insensitive)
    List<User> findByEmailContainingIgnoreCase(String email);

    // Find a list of users whose first name contains the given string (case-insensitive)
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
}
