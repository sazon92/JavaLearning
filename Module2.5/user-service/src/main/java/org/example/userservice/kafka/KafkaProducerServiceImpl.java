package org.example.userservice.kafka;

import org.example.shared.KafkaEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;

    public KafkaProducerServiceImpl(KafkaTemplate<String, KafkaEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendEvent(String topic, KafkaEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
