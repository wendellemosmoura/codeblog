package com.spring.codeblog.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] AUTH_LIST = {
            "/",
            "/posts",
            "/posts/{id}"
    };

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_LIST)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin().permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        return http.build();
    }

    @Bean
    protected InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        List<UserDetails> userDetails = new ArrayList<>();
        List<GrantedAuthority> adminRoles = new ArrayList<>();
        adminRoles.add(new SimpleGrantedAuthority("ADMIN"));

        userDetails.add(new User("admin", passwordEncoder().encode("admin"), adminRoles));

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return (web) -> web.ignoring().requestMatchers("/bootstrap/**");
    }
}
