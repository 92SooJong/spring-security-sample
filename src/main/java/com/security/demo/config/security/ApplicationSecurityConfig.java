package com.security.demo.config.security;

import com.security.demo.user.repository.UserRepositoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.concurrent.TimeUnit;

import static com.security.demo.config.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryUserDetailsService userRepositoryUserDetailsService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, UserRepositoryUserDetailsService userRepositoryUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepositoryUserDetailsService = userRepositoryUserDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.and()
                .authorizeRequests() // http요청에 대해 인증 검사를 하겠다
                .antMatchers("/" ,"index","/**","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest() // 모든요청은
                .authenticated() // 인증이 되어야한다.
                .and() // 그리고
                //.httpBasic(); // 인증 메커니즘은 httpBasic을 따른다
                .formLogin() // 인증 메커니즘은 Form based Auth를 따른다.
                    .loginPage("/login").permitAll() // 나만의 로그인 페이지사용 하기
                    .defaultSuccessUrl("/courses",true)
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                    .key("somethingverysecured")//  default 14일 이지만 21일로 기간을 늘렸다.
                    .rememberMeParameter("remember-me")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication()
//                .withUser("linda")
//                .password(passwordEncoder.encode("123123"))
//                .authorities(ADMIN.name());

        auth.authenticationProvider(daoAuthenticationProvider());

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userRepositoryUserDetailsService);
        return provider;

    }


}
