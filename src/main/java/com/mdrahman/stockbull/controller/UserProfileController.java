package com.mdrahman.stockbull.controller;

import com.mdrahman.stockbull.model.User;
import com.mdrahman.stockbull.repository.UserRepository;
import com.mdrahman.stockbull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserProfileController {

    private final UserRepository userRepository;
    private final UserService userService; // Add the UserService field

    @Autowired
    public UserProfileController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService; // Initialize the UserService field
    }

    // Handler method to show the list of all users
    @GetMapping("/userlist")
    public String showUserList(Model model) {
        // Fetch user information from the database using UserRepository
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userlist";
    }

    // Handler method to display the user's profile page
    @GetMapping("/profile")
    public String showUserProfile(Model model) {
        // Fetch the logged-in user's email from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userRepository.findByEmail(userEmail);

        if (user == null) {
            // Handle the case where the user is not found with the given email.
            // You can show an error message or redirect to an appropriate page.
            return "user_not_found"; // Replace "user_not_found" with the appropriate view name.
        }

        model.addAttribute("user", user);
        return "profile";
    }

    // Handler method to show the edit profile form for a specific user
    @GetMapping("profile/edit/{id}")
    public String showEditProfileForm(@PathVariable("id") Long id, Model model) {
        // Find the user with the given id
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
            return "editprofile"; // Replace "edit_profile" with the name of the edit profile form view
        } else {
            // Handle the case where the user is not found with the given id.
            // You can show an error message or redirect to an appropriate page.
            return "user_not_found"; // Replace "user_not_found" with the appropriate view name.
        }
    }

    // Handler method to save the updated user profile
    @PostMapping("/save-profile")
    public String saveUserProfile(@ModelAttribute("user") User updatedUser) {
        // Fetch the existing user data from the database using the user ID
        Optional<User> optionalUser = userRepository.findById(updatedUser.getId());

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            // Update only the first name and last name fields
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());

            // Save the updated user information to the database
            userRepository.save(existingUser);
            return "redirect:/profile";
        } else {
            // Handle the case where the user is not found with the given ID.
            // You can show an error message or redirect to an appropriate page.
            return "user_not_found"; // Replace "user_not_found" with the appropriate view name.
        }
    }

    // Handler method to delete a user from the user list
    @DeleteMapping("/userlist/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        // Check if the user exists in the database
        User userToDelete = userRepository.findById(id).orElse(null);

        if (userToDelete == null) {
            // If the user does not exist, handle the error (you can display an error page or redirect to user list)
            return "redirect:/userlist";
        }

        // Call the deleteUser method from UserService to handle cascade deletion
        userService.deleteUser(id);

        // Redirect back to the user list page after deleting the user
        return "redirect:/userlist";
    }

    // Combined search method for searching users by email or first name
    @PostMapping("/userlist/search")
    public String searchUsers(@RequestParam("searchText") String searchText, Model model) {
        List<User> searchResults;

        // Determine if the search is by email or first name
        if (searchText.contains("@")) {
            searchResults = userService.searchByEmail(searchText);
        } else {
            searchResults = userService.searchByFirstName(searchText);
        }

        model.addAttribute("users", searchResults);
        return "userlist";
    }
}
