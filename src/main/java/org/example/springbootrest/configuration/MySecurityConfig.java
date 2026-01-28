package org.example.springbootrest.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("ivan")
                .password("{noop}ivan")
                .roles("EMPLOYEE")
                .build();

        UserDetails admin = User.builder()
                .username("elena")
                .password("{noop}elena")
                .roles("EMPLOYEE", "HR")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("HR")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("HR")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("HR")
                        .anyRequest().authenticated()
                );
        return  http.build();
    }
}
