package com.security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

import static com.security.demo.security.ApplicationUserPermission.COURSE_WRITE;
import static com.security.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.and()
                .authorizeRequests() // http요청에 대해 인증 검사를 하겠다
                .antMatchers("/" ,"index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                // /management/api/** 에 대한 삭제 요청은 COURSE_WRITE 권한을 가진 사용자만 수행이 가능하다.
//                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(),ADMINTRAINEE.name())
                .anyRequest() // 모든요청은
                .authenticated() // 인증이 되어야한다.
                .and() // 그리고
                //.httpBasic(); // 인증 메커니즘은 httpBasic을 따른다
                .formLogin() // 인증 메커니즘은 Form based Auth를 따른다.
                .loginPage("/login").permitAll() // 나만의 로그인 페이지사용 하기
                .defaultSuccessUrl("/courses",true)
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                    .key("somethingverysecured")//  default 14일 이지만 21일로 기간을 늘렸다.
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");



    }


    // 이렇게하면 웹 브라우저에서 기본적으로 제공하는 인증 요청 팝업이 뜬다.
    // 로그아웃 할 방법이 없음!

    @Override
    @Bean
    protected UserDetailsService userDetailsService() { // DB에 있는 사용자정보를 어떻게 처리할지 작성하는 메소드

        UserDetails sjhaUser = User.builder()
                .username("sjha")
                .password(passwordEncoder.encode("123123"))
//                .roles(STUDENT.name()) // ROLE_STUDENT
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("123123"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("123123"))
//                .roles(ADMINTRAINEE.name()) // Role 기준 권한부여
                .authorities(ADMINTRAINEE.getGrantedAuthorities()) // 권한 자체를 직접 부여
                .build();


        return new InMemoryUserDetailsManager(
                sjhaUser,
                lindaUser,
                tomUser
        );
    }
}
