package com.security.demo.user.controller;

import com.security.demo.user.repository.UserRepository;
import com.security.demo.user.dto.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public UserRegistrationController(UserRepository userRepo , PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("registerForm" , new RegistrationForm());
        return "/login/registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute RegistrationForm form) {
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
