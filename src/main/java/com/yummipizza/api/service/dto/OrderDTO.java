package com.yummipizza.api.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.yummipizza.api.domain.Order} entity.
 */
public class OrderDTO implements Serializable {
    
    private Long id;

    private LocalDate date;

    private Double totalPrice;

    private Boolean delivered;


    private Long addressId;

    private Long customerId;

    private Long pizzariaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPizzariaId() {
        return pizzariaId;
    }

    public void setPizzariaId(Long pizzariaId) {
        this.pizzariaId = pizzariaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDTO)) {
            return false;
        }

        return id != null && id.equals(((OrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", delivered='" + isDelivered() + "'" +
            ", addressId=" + getAddressId() +
            ", customerId=" + getCustomerId() +
            ", pizzariaId=" + getPizzariaId() +
            "}";
    }
}
