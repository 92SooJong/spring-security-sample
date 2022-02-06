package com.security.demo.domain.user;

import com.security.demo.domain.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser,Integer> {

    ApplicationUser findByUsername(String username);

}
