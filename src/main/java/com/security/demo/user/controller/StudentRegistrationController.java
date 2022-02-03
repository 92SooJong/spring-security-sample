package com.security.demo.user.controller;

import com.security.demo.user.repository.UserRepository;
import com.security.demo.user.dto.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class StudentRegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public StudentRegistrationController(UserRepository userRepo , PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("registerForm" , new RegistrationForm());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute RegistrationForm form) {
        System.out.println("form = " + form.toString());

        userRepo.save(form. toUser(passwordEncoder));
        return "redirect:/login";
    }

}
