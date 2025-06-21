package com.swiftcart.order_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swiftcart.order_service.entity.Order;
import com.swiftcart.order_service.entity.OrderItem;
import com.swiftcart.order_service.entity.event.OrderCreatedEvent;
import com.swiftcart.order_service.enums.OrderStatus;
import com.swiftcart.order_service.enums.PaymentStatus;
import com.swiftcart.order_service.mapper.OrderMapper;
import com.swiftcart.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducerService orderEventProducerService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderEventProducerService orderEventProducerService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderEventProducerService = orderEventProducerService;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public Order createOrder(Order order) throws JsonProcessingException {
        order.setOrderStatus(order.getOrderStatus() != null ? order.getOrderStatus() : OrderStatus.PENDING);
        order.setPaymentStatus(order.getPaymentStatus() != null ? order.getPaymentStatus() : PaymentStatus.PENDING);
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
        Order savedOrder = orderRepository.save(order);
        OrderCreatedEvent orderCreatedEvent1 = orderMapper.toOrderEvent(savedOrder);
        orderEventProducerService.publishOrderCreatedEvent(orderCreatedEvent1);
        return savedOrder;
    }
}
