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

    @Lob
    @Column(name = "aboutus")
    private String aboutus;

    @Column(name = "email")
    private String email;

    @Column(name = "open_hours")
    private String openHours;

    @Column(name = "open_days")
    private String openDays;

    @Column(name = "delivery_price_in_dollor")
    private Double deliveryPriceInDollor;

    @Column(name = "delivery_price_in_euro")
    private Double deliveryPriceInEuro;

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

    @OneToMany(mappedBy = "pizzaria", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "pizzaria", fetch = FetchType.EAGER)
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

    public String getAboutus() {
        return aboutus;
    }

    public Pizzaria aboutus(String aboutus) {
        this.aboutus = aboutus;
        return this;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getEmail() {
        return email;
    }

    public Pizzaria email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Double getDeliveryPriceInDollor() {
        return deliveryPriceInDollor;
    }

    public Pizzaria deliveryPriceInDollor(Double deliveryPriceInDollor) {
        this.deliveryPriceInDollor = deliveryPriceInDollor;
        return this;
    }

    public void setDeliveryPriceInDollor(Double deliveryPriceInDollor) {
        this.deliveryPriceInDollor = deliveryPriceInDollor;
    }

    public Double getDeliveryPriceInEuro() {
        return deliveryPriceInEuro;
    }

    public Pizzaria deliveryPriceInEuro(Double deliveryPriceInEuro) {
        this.deliveryPriceInEuro = deliveryPriceInEuro;
        return this;
    }

    public void setDeliveryPriceInEuro(Double deliveryPriceInEuro) {
        this.deliveryPriceInEuro = deliveryPriceInEuro;
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
            "}";
    }
}
