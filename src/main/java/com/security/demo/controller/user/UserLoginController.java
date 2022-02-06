package com.security.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserLoginController {

    @GetMapping("/login")
    public String getLoginView(){
        return "/login/login";
    }



}
