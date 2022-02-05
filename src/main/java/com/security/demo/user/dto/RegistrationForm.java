package com.security.demo.user.dto;

import com.security.demo.user.entity.ApplicationUser;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Data
public class RegistrationForm {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ApplicationUser toUser(PasswordEncoder passwordEncoder) {

        return new ApplicationUser(username, passwordEncoder.encode(password),"USER");
    }

}
