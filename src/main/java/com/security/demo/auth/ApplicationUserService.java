package com.security.demo.auth;

import com.security.demo.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {


    //private final ApplicationUserDao applicationUserDao;
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //ApplicationUser applicationUser = new ApplicationUser();

        //userRepository.findByUserName(username);
        // TODO - 작성하기
        return null;
    }
}
