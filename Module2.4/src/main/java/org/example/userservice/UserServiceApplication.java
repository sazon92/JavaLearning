package org.example.userservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMalformed().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(UserServiceApplication.class, args);
    }
}
