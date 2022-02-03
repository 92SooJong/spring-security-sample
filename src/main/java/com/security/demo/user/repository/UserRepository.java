package com.security.demo.user.repository;

import com.security.demo.user.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser,Integer> {

    ApplicationUser findByUsername(String username);

}
