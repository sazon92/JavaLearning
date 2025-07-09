package org.example.notificationservice.kafka;

import org.example.notificationservice.service.EmailService;
import org.example.shared.KafkaEvent;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "user-events", groupId = "notification-group")
public class KafkaConsumerService {

    private final EmailService emailService;

    public KafkaConsumerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaHandler
    public void handleEvent(KafkaEvent event) {
        String message = switch (event.operation()) {
            case "CREATE" -> "Здравствуйте! Ваш аккаунт на сайте был успешно создан.";
            case "DELETE" -> "Здравствуйте! Ваш аккаунт был удалён.";
            default -> null;
        };
        if (message != null) {
            emailService.sendEmail(event.email(), "Уведомление", message);
        }
    }
}
