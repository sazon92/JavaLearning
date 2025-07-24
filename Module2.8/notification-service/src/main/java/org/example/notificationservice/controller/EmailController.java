package org.example.notificationservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.notificationservice.dto.EmailDto;
import org.example.notificationservice.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    @CircuitBreaker(name = "emailServiceCB", fallbackMethod = "fallbackSendEmail")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailDto dto) {
        emailService.sendEmail(dto.to(), dto.subject(), dto.body());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> fallbackSendEmail(EmailDto dto, Throwable ex) {
        System.err.println("Fallback при отправке email: " + ex.getMessage());
        return ResponseEntity.status(503).build();
    }
}