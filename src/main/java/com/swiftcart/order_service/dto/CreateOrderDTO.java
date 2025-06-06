package com.swiftcart.order_service.dto;

import com.swiftcart.order_service.enums.OrderStatus;
import com.swiftcart.order_service.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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

    private OrderStatus orderStatus;

    private Map<String, Object> shippingAddress;

    @NotNull
    private PaymentStatus paymentStatus;

}
