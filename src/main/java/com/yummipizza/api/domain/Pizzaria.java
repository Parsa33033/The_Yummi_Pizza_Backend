package com.yummipizza.api.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Pizzaria.
 */
@Entity
@Table(name = "pizzaria")
public class Pizzaria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "open_hours")
    private String openHours;

    @Column(name = "open_days")
    private String openDays;

    @Column(name = "delivery_price")
    private Double deliveryPrice;

    @Column(name = "staff")
    private Integer staff;

    @Column(name = "customers")
    private Integer customers;

    @Column(name = "number_of_awards")
    private Integer numberOfAwards;

    @Column(name = "pizza_branches")
    private Integer pizzaBranches;

    @OneToOne
    @JoinColumn(unique = true)
    private Manager manager;

    @OneToOne
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "pizzaria")
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "pizzaria")
    private Set<MenuItem> items = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Pizzaria name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Pizzaria description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpenHours() {
        return openHours;
    }

    public Pizzaria openHours(String openHours) {
        this.openHours = openHours;
        return this;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public String getOpenDays() {
        return openDays;
    }

    public Pizzaria openDays(String openDays) {
        this.openDays = openDays;
        return this;
    }

    public void setOpenDays(String openDays) {
        this.openDays = openDays;
    }

    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public Pizzaria deliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
        return this;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Integer getStaff() {
        return staff;
    }

    public Pizzaria staff(Integer staff) {
        this.staff = staff;
        return this;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
    }

    public Integer getCustomers() {
        return customers;
    }

    public Pizzaria customers(Integer customers) {
        this.customers = customers;
        return this;
    }

    public void setCustomers(Integer customers) {
        this.customers = customers;
    }

    public Integer getNumberOfAwards() {
        return numberOfAwards;
    }

    public Pizzaria numberOfAwards(Integer numberOfAwards) {
        this.numberOfAwards = numberOfAwards;
        return this;
    }

    public void setNumberOfAwards(Integer numberOfAwards) {
        this.numberOfAwards = numberOfAwards;
    }

    public Integer getPizzaBranches() {
        return pizzaBranches;
    }

    public Pizzaria pizzaBranches(Integer pizzaBranches) {
        this.pizzaBranches = pizzaBranches;
        return this;
    }

    public void setPizzaBranches(Integer pizzaBranches) {
        this.pizzaBranches = pizzaBranches;
    }

    public Manager getManager() {
        return manager;
    }

    public Pizzaria manager(Manager manager) {
        this.manager = manager;
        return this;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Address getAddress() {
        return address;
    }

    public Pizzaria address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Pizzaria orders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public Pizzaria addOrders(Order order) {
        this.orders.add(order);
        order.setPizzaria(this);
        return this;
    }

    public Pizzaria removeOrders(Order order) {
        this.orders.remove(order);
        order.setPizzaria(null);
        return this;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<MenuItem> getItems() {
        return items;
    }

    public Pizzaria items(Set<MenuItem> menuItems) {
        this.items = menuItems;
        return this;
    }

    public Pizzaria addItems(MenuItem menuItem) {
        this.items.add(menuItem);
        menuItem.setPizzaria(this);
        return this;
    }

    public Pizzaria removeItems(MenuItem menuItem) {
        this.items.remove(menuItem);
        menuItem.setPizzaria(null);
        return this;
    }

    public void setItems(Set<MenuItem> menuItems) {
        this.items = menuItems;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pizzaria)) {
            return false;
        }
        return id != null && id.equals(((Pizzaria) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pizzaria{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", openHours='" + getOpenHours() + "'" +
            ", openDays='" + getOpenDays() + "'" +
            ", deliveryPrice=" + getDeliveryPrice() +
            ", staff=" + getStaff() +
            ", customers=" + getCustomers() +
            ", numberOfAwards=" + getNumberOfAwards() +
            ", pizzaBranches=" + getPizzaBranches() +
            "}";
    }
}
