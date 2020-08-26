package com.yummipizza.api.service.dto;

import com.yummipizza.api.domain.OrderItem;

public class DummyOrderItemDTO extends OrderItemDTO {

    DummyMenuItemDTO menuItem;

    public DummyOrderItemDTO() {
    }

    public DummyOrderItemDTO(OrderItemDTO orderItemDTO) {
        this.setId(orderItemDTO.getId());
        this.setMenuItemId(orderItemDTO.getMenuItemId());
        this.setNumber(orderItemDTO.getNumber());
        this.setOrderId(orderItemDTO.getOrderId());
    }
}
