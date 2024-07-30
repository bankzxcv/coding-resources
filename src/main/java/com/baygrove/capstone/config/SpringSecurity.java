package com.baygrove.capstone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurity {
    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(Customizer.withDefaults());
//        http.csrf(csrf -> csrf.disable());

        http.authorizeRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/admin/**"),
                        new AntPathRequestMatcher("/account/**")).authenticated()
                .anyRequest().permitAll();

        http.formLogin(formLogin -> formLogin
                .loginPage("/user/login") // GET
                .loginProcessingUrl("/user/login-process") // POST
        );

        http.logout(formLogout -> formLogout
                .invalidateHttpSession(true)
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/"));

        return http.build();
    }

}
