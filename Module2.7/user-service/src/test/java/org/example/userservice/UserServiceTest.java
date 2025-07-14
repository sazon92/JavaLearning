package org.example.userservice;

import org.example.shared.KafkaEvent;
import org.example.userservice.dto.UserDto;
import org.example.userservice.entity.User;
import org.example.userservice.kafka.KafkaProducerService;
import org.example.userservice.repository.UserRepository;
import org.example.userservice.service.UserService;
import org.example.userservice.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserRepository userRepository;
    private KafkaProducerService kafkaProducer;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        kafkaProducer = mock(KafkaProducerService.class);
        userService = new UserServiceImpl(userRepository, kafkaProducer);
    }

    @Test
    void testCreateUser() {
        UserDto inputDto = new UserDto(null, "Alice", "alice@example.com", 30);

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

        assertEquals("Alice", result.name());
        assertEquals("alice@example.com", result.email());
        assertEquals(30, result.age());
        assertEquals(1L, result.id());

        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<KafkaEvent> eventCaptor = ArgumentCaptor.forClass(KafkaEvent.class);
        verify(kafkaProducer).sendEvent(topicCaptor.capture(), eventCaptor.capture());

        assertEquals("user-events", topicCaptor.getValue());
        KafkaEvent event = eventCaptor.getValue();
        assertEquals("CREATE", event.operation());
        assertEquals("alice@example.com", event.email());
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
        assertEquals("Bob", result.name());
        assertEquals("bob@example.com", result.email());
        assertEquals(25, result.age());
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

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<UserDto> users = userService.findAll();

        assertEquals(2, users.size());
        assertEquals("Alice", users.get(0).name());
        assertEquals("Bob", users.get(1).name());
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Old Name");
        existingUser.setEmail("old@example.com");
        existingUser.setAge(20);

        UserDto updateDto = new UserDto(null, "New Name", "new@example.com", 30);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDto updated = userService.update(1L, updateDto);

        assertEquals("New Name", updated.name());
        assertEquals("new@example.com", updated.email());
        assertEquals(30, updated.age());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Charlie");
        user.setEmail("charlie@example.com");
        user.setAge(40);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.delete(1L);

        verify(userRepository).deleteById(1L);

        ArgumentCaptor<KafkaEvent> eventCaptor = ArgumentCaptor.forClass(KafkaEvent.class);
        verify(kafkaProducer).sendEvent(eq("user-events"), eventCaptor.capture());

        KafkaEvent event = eventCaptor.getValue();
        assertEquals("DELETE", event.operation());
        assertEquals("charlie@example.com", event.email());
    }
}
