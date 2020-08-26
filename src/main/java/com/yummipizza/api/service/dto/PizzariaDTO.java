package com.yummipizza.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.yummipizza.api.domain.Pizzaria} entity.
 */
public class PizzariaDTO implements Serializable {
    
    private Long id;

    private String name;

    @Lob
    private String description;

    @Lob
    private String aboutus;

    private String email;

    private String openHours;

    private String openDays;

    private Double deliveryPriceInDollor;

    private Double deliveryPriceInEuro;

    private Integer staff;

    private Integer customers;

    private Integer numberOfAwards;

    private Integer pizzaBranches;


    private Long managerId;

    private Long addressId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public String getOpenDays() {
        return openDays;
    }

    public void setOpenDays(String openDays) {
        this.openDays = openDays;
    }

    public Double getDeliveryPriceInDollor() {
        return deliveryPriceInDollor;
    }

    public void setDeliveryPriceInDollor(Double deliveryPriceInDollor) {
        this.deliveryPriceInDollor = deliveryPriceInDollor;
    }

    public Double getDeliveryPriceInEuro() {
        return deliveryPriceInEuro;
    }

    public void setDeliveryPriceInEuro(Double deliveryPriceInEuro) {
        this.deliveryPriceInEuro = deliveryPriceInEuro;
    }

    public Integer getStaff() {
        return staff;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
    }

    public Integer getCustomers() {
        return customers;
    }

    public void setCustomers(Integer customers) {
        this.customers = customers;
    }

    public Integer getNumberOfAwards() {
        return numberOfAwards;
    }

    public void setNumberOfAwards(Integer numberOfAwards) {
        this.numberOfAwards = numberOfAwards;
    }

    public Integer getPizzaBranches() {
        return pizzaBranches;
    }

    public void setPizzaBranches(Integer pizzaBranches) {
        this.pizzaBranches = pizzaBranches;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PizzariaDTO)) {
            return false;
        }

        return id != null && id.equals(((PizzariaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PizzariaDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", aboutus='" + getAboutus() + "'" +
            ", email='" + getEmail() + "'" +
            ", openHours='" + getOpenHours() + "'" +
            ", openDays='" + getOpenDays() + "'" +
            ", deliveryPriceInDollor=" + getDeliveryPriceInDollor() +
            ", deliveryPriceInEuro=" + getDeliveryPriceInEuro() +
            ", staff=" + getStaff() +
            ", customers=" + getCustomers() +
            ", numberOfAwards=" + getNumberOfAwards() +
            ", pizzaBranches=" + getPizzaBranches() +
            ", managerId=" + getManagerId() +
            ", addressId=" + getAddressId() +
            "}";
    }
}
