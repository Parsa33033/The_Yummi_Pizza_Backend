package com.yummipizza.api.service.dto;

import java.util.List;

public class DummyPizzariaDTO extends PizzariaDTO{

    DummyManagerDTO manager;
    DummyAddressDTO address;
    List<DummyOrderDTO> orders;
    List<DummyMenuItemDTO> items;

    public DummyPizzariaDTO() {
    }

    public DummyPizzariaDTO(PizzariaDTO pizzariaDTO) {
        this.setId(pizzariaDTO.getId());
        this.setAddressId(pizzariaDTO.getAddressId());
        this.setCustomers(pizzariaDTO.getCustomers());
        this.setDeliveryPrice(pizzariaDTO.getDeliveryPrice());
        this.setDescription(pizzariaDTO.getDescription());
        this.setManagerId(pizzariaDTO.getManagerId());
        this.setName(pizzariaDTO.getName());
        this.setNumberOfAwards(pizzariaDTO.getNumberOfAwards());
        this.setOpenDays(pizzariaDTO.getOpenDays());
        this.setOpenHours(pizzariaDTO.getOpenHours());
        this.setPizzaBranches(pizzariaDTO.getPizzaBranches());
        this.setStaff(pizzariaDTO.getStaff());
    }

    public DummyManagerDTO getManager() {
        return manager;
    }

    public void setManager(DummyManagerDTO manager) {
        this.manager = manager;
    }

    public DummyAddressDTO getAddress() {
        return address;
    }

    public void setAddress(DummyAddressDTO address) {
        this.address = address;
    }

    public List<DummyOrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<DummyOrderDTO> orders) {
        this.orders = orders;
    }

    public List<DummyMenuItemDTO> getItems() {
        return items;
    }

    public void setItems(List<DummyMenuItemDTO> items) {
        this.items = items;
    }
}
