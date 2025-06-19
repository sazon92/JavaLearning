package org.example.userservice;

import org.example.userservice.dao.UserDao;
import org.example.userservice.entity.User;
import org.example.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserDao userDaoMock;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDaoMock = mock(UserDao.class);
        userService = new UserService(userDaoMock);
    }

    @Test
    void testCreateUser() {
        User created = userService.createUser("Alice", "alice@example.com", 30);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userDaoMock).create(captor.capture());

        User user = captor.getValue();
        assertEquals("Alice", user.getName());
        assertEquals("alice@example.com", user.getEmail());
        assertEquals(30, user.getAge());
    }

    @Test
    void testGetUser() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Bob");

        when(userDaoMock.read(1L)).thenReturn(mockUser);

        User result = userService.getUser(1L);

        assertNotNull(result);
        assertEquals("Bob", result.getName());
        verify(userDaoMock).read(1L);
    }

    @Test
    void testGetAllUsers() {
        User u1 = new User(); u1.setName("A");
        User u2 = new User(); u2.setName("B");

        when(userDaoMock.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        verify(userDaoMock).findAll();
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Updated");

        userService.updateUser(user);

        verify(userDaoMock).update(user);
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userDaoMock).delete(1L);
    }
}
