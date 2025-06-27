package org.example.userservice;

import org.example.userservice.dto.UserDto;
import org.example.userservice.entity.User;
import org.example.userservice.repository.UserRepository;
import org.example.userservice.service.UserService;
import org.example.userservice.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testCreateUser() {
        UserDto inputDto = new UserDto();
        inputDto.setName("Alice");
        inputDto.setEmail("alice@example.com");
        inputDto.setAge(30);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Alice");
        savedUser.setEmail("alice@example.com");
        savedUser.setAge(30);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserDto result = userService.create(inputDto);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User capturedUser = captor.getValue();
        assertEquals("Alice", capturedUser.getName());
        assertEquals("alice@example.com", capturedUser.getEmail());
        assertEquals(30, capturedUser.getAge());

        assertEquals("Alice", result.getName());
        assertEquals("alice@example.com", result.getEmail());
        assertEquals(30, result.getAge());
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindById() {
        User user = new User();
        user.setId(1L);
        user.setName("Bob");
        user.setEmail("bob@example.com");
        user.setAge(25);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDto result = userService.findById(1L);

        assertNotNull(result);
        assertEquals("Bob", result.getName());
        assertEquals("bob@example.com", result.getEmail());
        assertEquals(25, result.getAge());
    }

    @Test
    void testFindAll() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Alice");
        user1.setEmail("alice@example.com");
        user1.setAge(30);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Bob");
        user2.setEmail("bob@example.com");
        user2.setAge(25);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDto> users = userService.findAll();

        assertEquals(2, users.size());
        assertEquals("Alice", users.get(0).getName());
        assertEquals("Bob", users.get(1).getName());
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Old Name");
        existingUser.setEmail("old@example.com");
        existingUser.setAge(20);

        UserDto updateDto = new UserDto();
        updateDto.setName("New Name");
        updateDto.setEmail("new@example.com");
        updateDto.setAge(30);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDto updated = userService.update(1L, updateDto);

        assertEquals("New Name", updated.getName());
        assertEquals("new@example.com", updated.getEmail());
        assertEquals(30, updated.getAge());
    }

    @Test
    void testDeleteUser() {
        userService.delete(1L);
        verify(userRepository).deleteById(1L);
    }
}
