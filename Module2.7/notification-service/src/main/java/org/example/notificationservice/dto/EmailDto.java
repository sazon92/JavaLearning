package org.example.notificationservice.dto;

/**
 * DTO для передачи данных email сообщения.
 *
 * @param to      адрес электронной почты получателя
 * @param subject тема письма
 * @param body    содержимое письма
 */
public record EmailDto(String to, String subject, String body) {}