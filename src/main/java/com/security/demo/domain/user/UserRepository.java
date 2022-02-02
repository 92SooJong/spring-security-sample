package com.security.demo.domain.user;

import com.security.demo.auth.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser,Integer> {

    ApplicationUser findByUserName(String username);

}
