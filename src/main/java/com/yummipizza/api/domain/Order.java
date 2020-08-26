package com.yummipizza.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Order.
 */
@Entity
@Table(name = "jhi_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "delivered")
    private Boolean delivered;

    @OneToOne
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    private Customer customer;

    @ManyToOne
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    private Pizzaria pizzaria;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Order date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Order totalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean isDelivered() {
        return delivered;
    }

    public Order delivered(Boolean delivered) {
        this.delivered = delivered;
        return this;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Address getAddress() {
        return address;
    }

    public Order address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public Order items(Set<OrderItem> orderItems) {
        this.items = orderItems;
        return this;
    }

    public Order addItems(OrderItem orderItem) {
        this.items.add(orderItem);
        orderItem.setOrder(this);
        return this;
    }

    public Order removeItems(OrderItem orderItem) {
        this.items.remove(orderItem);
        orderItem.setOrder(null);
        return this;
    }

    public void setItems(Set<OrderItem> orderItems) {
        this.items = orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Pizzaria getPizzaria() {
        return pizzaria;
    }

    public Order pizzaria(Pizzaria pizzaria) {
        this.pizzaria = pizzaria;
        return this;
    }

    public void setPizzaria(Pizzaria pizzaria) {
        this.pizzaria = pizzaria;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", delivered='" + isDelivered() + "'" +
            "}";
    }
}
