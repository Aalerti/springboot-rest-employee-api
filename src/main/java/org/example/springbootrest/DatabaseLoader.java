package org.example.springbootrest;

import org.example.springbootrest.dao.UserRepository;
import org.example.springbootrest.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User ivan = new User("ivan", passwordEncoder.encode("ivan"), "EMPLOYEE");
            User elena = new User("elena", passwordEncoder.encode("elena"), "HR");

            userRepository.saveAll(List.of(ivan, elena));
            System.out.println("Пользователи созданы в базе данных!");
        }
    }
}