package org.example.userservice.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) для передачи данных пользователя
 */
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private int age;

    public UserDto() {
        // Пустой конструктор по умолчанию
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

}
