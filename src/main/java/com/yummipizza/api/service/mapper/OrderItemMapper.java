package com.yummipizza.api.service.mapper;


import com.yummipizza.api.domain.*;
import com.yummipizza.api.service.dto.OrderItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderItem} and its DTO {@link OrderItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {MenuItemMapper.class, OrderMapper.class})
public interface OrderItemMapper extends EntityMapper<OrderItemDTO, OrderItem> {

    @Mapping(source = "menuItem.id", target = "menuItemId")
    @Mapping(source = "order.id", target = "orderId")
    OrderItemDTO toDto(OrderItem orderItem);

    @Mapping(source = "menuItemId", target = "menuItem")
    @Mapping(source = "orderId", target = "order")
    OrderItem toEntity(OrderItemDTO orderItemDTO);

    default OrderItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setId(id);
        return orderItem;
    }
}
