package org.example.userservice.dto;

/**
 * Data Transfer Object (DTO) для пользователя.
 *
 * @param id    Идентификатор пользователя
 * @param name  Имя пользователя
 * @param email Адрес электронной почты
 * @param age   Возраст пользователя
 */
public record UserDto(
        Long id,
        String name,
        String email,
        int age
){
}
