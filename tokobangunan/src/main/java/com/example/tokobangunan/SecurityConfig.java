package com.example.tokobangunan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Bebaskan file aset UI
                .anyRequest().authenticated() // Kunci semua halaman kasir & penjualan
            )
            .formLogin((form) -> form
                .loginPage("/login") 
                .defaultSuccessUrl("/", true) 
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .sessionManagement((session) -> session
                .maximumSessions(1)
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
            .username("adminfajar")
            .password("{noop}fajarbaru2026") // {noop} artinya No-Op (No Operation) Encoder, aman & bersih
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(admin);
    }
}