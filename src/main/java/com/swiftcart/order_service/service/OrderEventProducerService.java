package com.swiftcart.order_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiftcart.order_service.entity.event.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderEventProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderEventProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Value("${order.topic.name:order-events}")
    private String topicName;

    public void publishOrderCreatedEvent(OrderCreatedEvent event) throws JsonProcessingException {
        event.setEventType("ORDER_CREATED");
        event.setRequestId(UUID.randomUUID().toString());
        String eventJson = objectMapper.writeValueAsString(event);
        kafkaTemplate.send(topicName, event.getRequestId(), eventJson);
        System.out.println("Published OrderCreated Event to Kafka: " + eventJson);
    }
}
