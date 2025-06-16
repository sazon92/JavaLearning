package org.example.userservice.dao;

import org.example.userservice.entity.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    User read(Long id);
    void update(User user);
    void delete(Long id);
    List<User> findAll();
}
