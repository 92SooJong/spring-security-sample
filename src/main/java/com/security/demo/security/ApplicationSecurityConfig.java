package com.security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.security.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // http요청에 대해 인증 검사를 하겠다
                .antMatchers("/" ,"index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest() // 모든요청은
                .authenticated() // 인증이 되어야한다.
                .and() // 그리고
                .httpBasic(); // 인증 메커니즘은 httpBasic을 따른다
        // 이렇게하면 웹 브라우저에서 기본적으로 제공하는 인증 요청 팝업이 뜬다.
        // 로그아웃 할 방법이 없음!
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() { // DB에 있는 사용자정보를 어떻게 처리할지 작성하는 메소드

        UserDetails sjhaUser = User.builder()
                .username("sjha")
                .password(passwordEncoder.encode("123123"))
                .roles(STUDENT.name()) // ROLE_STUDENT
                .build();

        UserDetails lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("123123"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(
                sjhaUser,
                lindaUser
        );
    }
}
