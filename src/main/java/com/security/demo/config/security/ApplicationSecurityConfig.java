package com.security.demo.config.security;

import com.security.demo.domain.user.UserRepositoryUserDetailsService;
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
                .authorizeRequests() // http요청에 대해 인증 검사를 하겠다
                .antMatchers("/resources/templates/login/**","/css/*","/js/*").permitAll() // 인증이 필요없는 화면 리소스
                .antMatchers("/login/**", "/user-registration/**").permitAll() // 인증이 필요없는 API
                .anyRequest() // 모든요청은
                .authenticated() // 인증이 되어야한다.
                .and()
                .formLogin() // 인증 메커니즘은 Form based Auth를 따른다.
                    .loginPage("/login").permitAll() // 나만의 로그인 페이지사용 하기
                    .defaultSuccessUrl("/post/list",true)// 로그인 성공시 이동
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
                    .deleteCookies("JSESSIONID", "remember-me") // 로그아웃하면 2개의 쿠키를 삭제한다
                    .logoutSuccessUrl("/login"); // 로그아웃 성공시 로그인 페이지로 이동
        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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
