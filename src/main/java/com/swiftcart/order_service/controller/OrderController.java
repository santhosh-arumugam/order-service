package com.swiftcart.order_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swiftcart.order_service.dto.CreateOrderDTO;
import com.swiftcart.order_service.dto.OrderResponseDTO;
import com.swiftcart.order_service.entity.Order;
import com.swiftcart.order_service.mapper.OrderMapper;
import com.swiftcart.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid CreateOrderDTO dto) throws JsonProcessingException {
//        Order orderEntity = orderMapper.toEntity(dto);
        Order savedOrder = orderService.createOrder(orderMapper.toEntity(dto));
        OrderResponseDTO response = orderMapper.toResponseDto(savedOrder);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
