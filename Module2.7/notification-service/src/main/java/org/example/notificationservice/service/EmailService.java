package org.example.notificationservice.service;

public interface EmailService {
    /**
     * Отправляет email сообщение указанному получателю.
     *
     * @param to      адрес электронной почты получателя
     * @param subject тема письма
     * @param body    содержимое письма
     */
    void sendEmail(String to, String subject, String body);
}