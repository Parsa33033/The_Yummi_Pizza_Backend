package com.yummipizza.api.service.dto;

import java.util.List;

public class DummyCustomerDTO extends CustomerDTO{

    List<DummyOrderDTO> orders;
    DummyAddressDTO address;

    public DummyCustomerDTO() {
    }

    public DummyCustomerDTO(CustomerDTO customerDTO) {
        this.setId(customerDTO.getId());
        this.setAddressId(customerDTO.getAddressId());
        this.setEmail(customerDTO.getEmail());
        this.setFirstName(customerDTO.getFirstName());
        this.setLastName(customerDTO.getLastName());
        this.setGender(customerDTO.getGender());
        this.setImage(customerDTO.getImage());
        this.setImageContentType(customerDTO.getImageContentType());
        this.setMobileNumber(customerDTO.getMobileNumber());
        this.setUsername(customerDTO.getUsername());
    }

    public List<DummyOrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<DummyOrderDTO> orders) {
        this.orders = orders;
    }

    public DummyAddressDTO getAddress() {
        return address;
    }

    public void setAddress(DummyAddressDTO address) {
        this.address = address;
    }
}
