package com.swiftcart.order_service.mapper;

import com.swiftcart.order_service.dto.CreateOrderDTO;
import com.swiftcart.order_service.dto.OrderItemDTO;
import com.swiftcart.order_service.dto.OrderResponseDTO;
import com.swiftcart.order_service.entity.Order;
import com.swiftcart.order_service.entity.OrderItem;
import com.swiftcart.order_service.entity.event.OrderCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "orderDateTime", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "orderItems", target = "orderItems")
    Order toEntity(CreateOrderDTO dto);

    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toOrderItemEntity(OrderItemDTO dto);

    OrderResponseDTO toResponseDto(Order order);

    OrderCreatedEvent toOrderEvent(Order order);
}
