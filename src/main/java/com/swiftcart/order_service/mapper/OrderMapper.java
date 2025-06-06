package com.swiftcart.order_service.mapper;

import com.swiftcart.order_service.dto.CreateOrderDTO;
import com.swiftcart.order_service.dto.OrderResponseDTO;
import com.swiftcart.order_service.entity.Order;
import com.swiftcart.order_service.entity.event.OrderCreatedEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(CreateOrderDTO dto);

    OrderResponseDTO toResponseDto(Order order);

    OrderCreatedEvent toOrderEvent(Order order);
}
