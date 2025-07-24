package org.example.userservice.kafka;

import org.example.shared.KafkaEvent;;

public interface KafkaProducerService {

    /**
     * Отправляет событие в указанный топик Kafka.
     *
     * @param topic имя Kafka-топика, в который будет отправлено событие
     * @param event объект события для отправки
     */
    void sendEvent(String topic, KafkaEvent event);
}
