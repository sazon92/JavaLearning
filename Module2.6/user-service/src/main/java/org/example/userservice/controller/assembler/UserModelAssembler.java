package org.example.userservice.controller.assembler;

import org.example.userservice.controller.UserController;
import org.example.userservice.dto.UserDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserDto, EntityModel<UserDto>> {

    @Override
    public EntityModel<UserDto> toModel(UserDto user) {
        Long userId = user.id();

        return EntityModel.of(user,
                // ссылка на текущего пользователя
                linkTo(methodOn(UserController.class).getById(userId)).withSelfRel(),

                // ссылка на список всех пользователей
                linkTo(methodOn(UserController.class).getAll()).withRel("all-users"),

                // ссылка на создание нового пользователя
                linkTo(methodOn(UserController.class).create(null)).withRel("create-user"),

                // ссылка на обновление текущего пользователя
                linkTo(methodOn(UserController.class).update(userId, null)).withRel("update-user"),

                // ссылка на удаление текущего пользователя
                linkTo(UserController.class).slash(userId).withRel("delete-user").withType("DELETE")
        );
    }
}
