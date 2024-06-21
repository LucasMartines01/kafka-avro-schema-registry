package com.lucasmartines.kafka.avro.consumer;

import com.lucasmartines.kafka.avro.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaAvroConsumer {

    @KafkaListener(topics = "${topic.name}")
    public void listen(ConsumerRecord<String, Employee> consumerRecord) {
        String key = consumerRecord.key();
        Employee value = consumerRecord.value();
        log.info("Received message: key={}, value={}", key, value.toString());
    }
}
