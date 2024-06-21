package com.lucasmartines.kafka.avro.controller;

import com.lucasmartines.kafka.avro.dto.Employee;
import com.lucasmartines.kafka.avro.producer.KafkaAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private KafkaAvroProducer kafkaAvroProducer;

    public EventController(KafkaAvroProducer kafkaAvroProducer) {
        this.kafkaAvroProducer = kafkaAvroProducer;
    }

    @PostMapping("/events")
    public String sendEvent(@RequestBody Employee employee) {
        kafkaAvroProducer.send(employee);
        return "Event sent";
    }
}
