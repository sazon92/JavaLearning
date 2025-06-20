package org.example.userservice;

import org.example.userservice.dao.UserDaoImpl;
import org.example.userservice.entity.User;
import org.example.userservice.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoImplIntegrationTest {

    private UserDaoImpl userDao;

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.3")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @BeforeAll
    void setUp() {
        postgres.start();

        System.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        System.setProperty("hibernate.connection.username", postgres.getUsername());
        System.setProperty("hibernate.connection.password", postgres.getPassword());

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        userDao = new UserDaoImpl();
    }

    @AfterAll
    void tearDown() {
        postgres.stop();
    }

    @Test
    void testCreateAndReadUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setAge(30);

        userDao.create(user);

        assertNotNull(user.getId(), "ID пользователя должен быть присвоен");

        User fetchedUser = userDao.read(user.getId());
        assertNotNull(fetchedUser, "Пользователь должен быть найден");
        assertEquals("John Doe", fetchedUser.getName());
        assertEquals("john@example.com", fetchedUser.getEmail());
        assertEquals(30, fetchedUser.getAge());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("jane@example.com");
        user.setAge(25);

        userDao.create(user);

        user.setName("Jane Updated");
        user.setAge(26);

        userDao.update(user);

        User updatedUser = userDao.read(user.getId());
        assertEquals("Jane Updated", updatedUser.getName());
        assertEquals(26, updatedUser.getAge());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setName("To Delete");
        user.setEmail("delete@example.com");
        user.setAge(40);

        userDao.create(user);

        Long userId = user.getId();
        userDao.delete(userId);

        User deletedUser = userDao.read(userId);
        assertNull(deletedUser, "Пользователь должен быть удалён");
    }

    @Test
    void testFindAll() {
        List<User> usersBefore = userDao.findAll();

        User user1 = new User();
        user1.setName("User 1");
        user1.setEmail("u1@example.com");
        user1.setAge(22);

        User user2 = new User();
        user2.setName("User 2");
        user2.setEmail("u2@example.com");
        user2.setAge(23);

        userDao.create(user1);
        userDao.create(user2);

        List<User> usersAfter = userDao.findAll();

        assertNotNull(usersAfter);
        assertTrue(usersAfter.size() >= usersBefore.size() + 2, "В базе должно стать больше пользователей");
    }
}
