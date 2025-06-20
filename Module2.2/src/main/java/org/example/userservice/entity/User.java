package org.example.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Класс, представляющий сущность пользователя в системе.
 * Соответствует таблице "users" в базе данных.
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * Уникальный идентификатор пользователя.
     * Генерируется автоматически при сохранении в базу данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /**
     * Имя пользователя.
     * Не может быть null.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Электронная почта пользователя.
     * Должна быть уникальной в системе.
     */
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    /**
     * Возраст пользователя.
     * Должен быть положительным числом.
     */
    @Column(name = "age", nullable = false)
    private int age;

    /**
     * Дата и время создания записи о пользователе.
     * Устанавливается автоматически при создании объекта.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}