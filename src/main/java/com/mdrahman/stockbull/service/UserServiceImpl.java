package com.mdrahman.stockbull.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mdrahman.stockbull.dto.UserRegistrationDto;
import com.mdrahman.stockbull.model.Role;
import com.mdrahman.stockbull.model.User;
import com.mdrahman.stockbull.repository.RoleRepository;
import com.mdrahman.stockbull.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    // Autowired UserRepository
    @Autowired
    private UserRepository userRepository;

    // Autowired RoleRepository
    @Autowired
    private RoleRepository roleRepository;

    // Autowired BCryptPasswordEncoder
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Find a user by their email address.
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Save a new user registration to the database.
    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));

        // Get the selected role from the registration form
        String roleName = registration.getRole();
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
        user.setRoles(Arrays.asList(role));

        return userRepository.save(user);
    }

    // Load user details by their email address for Spring Security authentication.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    // Map roles to Spring Security authorities.
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    // Delete a user by their unique ID.
    @Override
    public void deleteUser(Long id) {
        User userToDelete = userRepository.findById(id).orElse(null);
        if (userToDelete != null) {
            // Remove the user's roles from the users_roles table
            userToDelete.setRoles(null);
            userRepository.save(userToDelete); // Save the user without roles (this will remove the associated entries in the users_roles table)
            userRepository.delete(userToDelete); // Delete the user
        }
    }

    // Search for users by their email address, returning a list of matching users.
    public List<User> searchByEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    // Search for users by their first name, returning a list of matching users.
    public List<User> searchByFirstName(String firstName) {
        return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    }
}