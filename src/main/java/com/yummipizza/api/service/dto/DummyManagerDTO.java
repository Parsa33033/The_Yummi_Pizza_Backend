package com.yummipizza.api.service.dto;

public class DummyManagerDTO extends ManagerDTO {

    public DummyManagerDTO() {
    }

    public DummyManagerDTO(ManagerDTO managerDTO) {
        this.setId(managerDTO.getId());
        this.setEmail(managerDTO.getEmail());
        this.setFirstName(managerDTO.getFirstName());
        this.setGender(managerDTO.getGender());
        this.setImage(managerDTO.getImage());
        this.setImageContentType(managerDTO.getImageContentType());
        this.setLastName(managerDTO.getLastName());
        this.setMobileNumber(managerDTO.getMobileNumber());
        this.setUsername(managerDTO.getUsername());
    }
}
