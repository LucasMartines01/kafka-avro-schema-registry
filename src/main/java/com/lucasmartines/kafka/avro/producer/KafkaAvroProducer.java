package com.lucasmartines.kafka.avro.producer;

import com.lucasmartines.kafka.avro.dto.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
public class KafkaAvroProducer {
    @Value("${topic.name}")
    private String topic;

    private KafkaTemplate<String, Employee> kafkaTemplate;

    Logger logger = Logger.getLogger(KafkaAvroProducer.class.getName());

    public KafkaAvroProducer(KafkaTemplate<String, Employee> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Employee employee) {
        CompletableFuture<SendResult<String, Employee>> send = kafkaTemplate.send(topic, UUID.randomUUID().toString(), employee);
        send.whenComplete((result, ex) -> {
            if (ex != null) {
                logger.severe("Error sending message: " + ex.getMessage());
            } else {
                logger.info("Message sent: " + result.getProducerRecord().value());
            }
        });
    }
}
