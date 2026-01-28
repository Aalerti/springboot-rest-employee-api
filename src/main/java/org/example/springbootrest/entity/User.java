package org.example.springbootrest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true) // Логин должен быть уникальным
    private String username;

    @Column(name = "password") // Тут будет храниться ХЕШ пароля, а не сам пароль
    private String password;

    @Column(name = "role") // Для простоты будем хранить роль одной строкой (например, "ROLE_HR")
    private String role;

    // Поле enabled нужно Спрингу, чтобы знать, не забанен ли пользователь
    @Column(name = "enabled")
    private boolean enabled = true;

    // Конструктор для удобства создания
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
