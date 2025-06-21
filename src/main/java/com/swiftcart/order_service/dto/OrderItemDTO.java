package com.swiftcart.order_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDTO {
    @NotNull(message = "Product ID should not be null")
    private Long productId;

    @Positive(message = "Quantity should be positive")
    @NotNull(message = "Quantity should not be null")
    private Integer quantity;

    @Positive(message = "Item Price should be positive")
    @NotNull(message = "Item Price should not be null")
    private BigDecimal unitPrice;

    @Positive(message = "Subtotal should be positive")
    @NotNull(message = "Subtotal should not be null")
    private BigDecimal subTotal;
}
