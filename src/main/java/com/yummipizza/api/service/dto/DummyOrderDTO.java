package com.yummipizza.api.service.dto;

import java.util.List;

public class DummyOrderDTO extends OrderDTO {

    List<DummyOrderItemDTO> items;

    public DummyOrderDTO() {
    }

    public DummyOrderDTO(OrderDTO orderDTO) {
        this.setId(orderDTO.getId());
        this.setAddressId(orderDTO.getAddressId());
        this.setCustomerId(orderDTO.getCustomerId());
        this.setDate(orderDTO.getDate());
        this.setDelivered(orderDTO.isDelivered());
        this.setPizzariaId(orderDTO.getPizzariaId());
        this.setTotalPrice(orderDTO.getTotalPrice());
    }

    public List<DummyOrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<DummyOrderItemDTO> items) {
        this.items = items;
    }
}
