package org.example.userservice.service;

import org.example.userservice.dto.UserDto;

import java.util.List;

/**
 * Сервисный интерфейс для управления пользователями.
 * Предоставляет методы для получения, создания, обновления и удаления пользователей.
 */
public interface UserService {

    /**
     * Получает список всех пользователей.
     *
     * @return список DTO всех пользователей
     */
    List<UserDto> findAll();

    /**
     * Находит пользователя по его идентификатору.
     *
     * @param id идентификатор пользователя
     * @return DTO найденного пользователя
     */
    UserDto findById(Long id);

    /**
     * Создаёт нового пользователя на основе переданных данных.
     *
     * @param dto DTO с данными нового пользователя
     * @return DTO созданного пользователя
     */
    UserDto create(UserDto dto);

    /**
     * Обновляет данные пользователя с указанным идентификатором.
     *
     * @param id  идентификатор пользователя
     * @param dto DTO с новыми данными пользователя
     * @return DTO обновлённого пользователя
     */
    UserDto update(Long id, UserDto dto);

    /**
     * Удаляет пользователя с указанным идентификатором.
     *
     * @param id идентификатор пользователя
     */
    void delete(Long id);
}
