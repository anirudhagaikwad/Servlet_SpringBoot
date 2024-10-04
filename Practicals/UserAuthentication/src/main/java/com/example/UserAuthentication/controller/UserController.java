package com.example.UserAuthentication.controller;

import com.example.UserAuthentication.service.UserService;
import com.example.UserAuthentication.model.Auth;
import com.example.UserAuthentication.model.Group;
import com.example.UserAuthentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	  @Autowired
	    private SessionRepository<? extends Session> sessionRepository;
	  
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registerUser";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, @RequestParam("groupId") Long groupId) {
        userService.registerUser(user, groupId);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User authenticatedUser = userService.findUserByUsername(currentUsername);
        Group group = userService.findGroupByUserId(authenticatedUser.getUserId());

        // Save user and group details in the Spring Session
        Session session = sessionRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if (session != null) {
            session.setAttribute("user", authenticatedUser);
            session.setAttribute("group", group);
            sessionRepository.save(session);
        }
        
        model.addAttribute("user", authenticatedUser);
        model.addAttribute("group", group);

        return "home";
    }
}
