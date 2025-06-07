package com.swiftcart.order_service.entity.event;

import com.swiftcart.order_service.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private Long customerId;
    private List<OrderItem> items;
    private BigDecimal totalAmount;
    private LocalDateTime orderDateTime;
    private String status;
}
