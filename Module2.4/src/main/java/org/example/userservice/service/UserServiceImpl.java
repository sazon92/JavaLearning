package org.example.userservice.service;

import org.example.userservice.dto.UserDto;
import org.example.userservice.entity.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDto create(UserDto dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setAge(dto.age());
        return toDto(repository.save(user));
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setAge(dto.age());
        return toDto(repository.save(user));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }
}
