package com.swiftcart.order_service.dto;

import com.swiftcart.order_service.enums.OrderStatus;
import com.swiftcart.order_service.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderDTO {

    @NotNull(message = "Customer ID should not be null")
    private Long customerId;

    @NotNull(message = "Total Amount should not be null")
    @Positive(message = "Total Amount should be positive")
    private BigDecimal totalAmount;

    @NotNull(message = "Order Status should not be null")
    private OrderStatus orderStatus;

    @NotNull(message = "Shipping Address should not be null")
    private Map<String, Object> shippingAddress;

    @NotNull(message = "Payment status should not be null")
    private PaymentStatus paymentStatus;

    @NotNull(message = "Order Items should not be null")
    private List<OrderItemDTO> orderItems;
}
