package com.yummipizza.api.service.dto;

public class DummyAddressDTO extends AddressDTO{

    public DummyAddressDTO() {
    }

    public DummyAddressDTO(AddressDTO addressDTO) {
        this.setId(addressDTO.getId());
        this.setAddress1(addressDTO.getAddress1());
        this.setAddress2(addressDTO.getAddress2());
        this.setCity(addressDTO.getCity());
        this.setCountry(addressDTO.getCountry());
        this.setPhoneNumber(addressDTO.getPhoneNumber());
        this.setState(addressDTO.getState());
    }
}
