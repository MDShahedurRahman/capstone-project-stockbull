package com.mdrahman.stockbull.controller;

import com.mdrahman.stockbull.dto.UserRegistrationDto;
import com.mdrahman.stockbull.model.User;
import com.mdrahman.stockbull.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    // ModelAttribute to provide a new instance of UserRegistrationDto to the view.
    // This method ensures that the "user" model attribute is available when rendering the view.
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    // Handler method to display the user registration form.
    @GetMapping
    public String showRegistrationForm(Model model) {
        // The "signup" template (view) will be rendered when accessing the "/signup" endpoint.
        return "signup";
    }

    // Handler method to process the user registration form submission.
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {

        // Check if the email provided by the user is already registered.
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            // If an account with the same email already exists, add an error message to the result object.
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        // Check if there are any validation errors in the userDto object.
        if (result.hasErrors()) {
            // If there are errors, return to the signup form to display the error messages.
            return "signup";
        }

        // Save the user's registration details to the database using the userService.
        userService.save(userDto);

        // Redirect the user to the signup page with a success parameter.
        // This will display a success message after a successful registration.
        return "redirect:/signup?success";
    }
}
