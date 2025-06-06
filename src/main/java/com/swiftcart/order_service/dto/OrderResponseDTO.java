package com.swiftcart.order_service.dto;

import com.swiftcart.order_service.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private Long customerId;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private LocalDateTime orderDateTime;
}
