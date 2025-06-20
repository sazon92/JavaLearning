package org.example.userservice.service;

import org.example.userservice.dao.UserDao;
import org.example.userservice.entity.User;

import java.util.List;

/**
 * Сервис для управления пользователями.
 * Делегирует операции сохранения данных в {@link UserDao}.
 */
public class UserService {
    private final UserDao userDao;

    /**
     * Конструктор для создания экземпляра UserService.
     *
     * @param userDao DAO для работы с данными пользователей. Не должен быть {@code null}.
     */
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Создает нового пользователя с указанными данными и сохраняет его.
     *
     * @param name  Имя пользователя.
     * @param email Email пользователя.
     * @param age   Возраст пользователя.
     * @return Созданный пользователь.
     */
    public User createUser(String name, String email, int age) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        userDao.create(user);
        return user;
    }

    /**
     * Возвращает пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя. Не должен быть {@code null}.
     * @return Найденный пользователь или {@code null}, если пользователь не существует.
     */
    public User getUser(Long id) {
        return userDao.read(id);
    }

    /**
     * Возвращает список всех пользователей.
     *
     * @return Список пользователей. Если пользователей нет, возвращает пустой список.
     */
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    /**
     * Обновляет данные пользователя.
     *
     * @param user Пользователь с обновленными данными. Не должен быть {@code null}.
     */
    public void updateUser(User user) {
        userDao.update(user);
    }

    /**
     * Удаляет пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя. Не должен быть {@code null}.
     */
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}