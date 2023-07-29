package com.mdrahman.stockbull.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Handler method for the root URL "/"
    @GetMapping("/")
    public String root() {
        return "index"; // Return the view name "index" to display the home page
    }

    // Handler method for the "/login" URL
    @GetMapping("/login")
    public String login(Model model) {
        return "login"; // Return the view name "login" to display the login page
    }

    // Handler method for the "/user" URL
    @GetMapping("/user")
    public String userIndex() {
        return "user/index"; // Return the view name "user/index" to display the user's index page
    }
}
