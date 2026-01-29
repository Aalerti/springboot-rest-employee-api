package org.example.springbootrest;

import org.example.springbootrest.dao.EmployeeRepository;
import org.example.springbootrest.dao.UserRepository;
import org.example.springbootrest.entity.Employee;
import org.example.springbootrest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseLoader(UserRepository userRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            Employee workerIvan = new Employee("Ivan", "Ivanov", "IT", 1000);
            employeeRepository.save(workerIvan);

            User userIvan = new User("ivan", passwordEncoder.encode("ivan"), "EMPLOYEE");
            userIvan.setEmployee(workerIvan);
            userRepository.save(userIvan);

            User adminElena = new User("elena", passwordEncoder.encode("elena"), "HR");
            userRepository.save(adminElena);

            System.out.println("Тестовые данные загружены: User 'ivan' связан с Employee 'Ivan Ivanov'");
        }
    }
}