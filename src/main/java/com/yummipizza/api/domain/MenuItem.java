package com.yummipizza.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

import com.yummipizza.api.domain.enumeration.FoodType;

/**
 * A MenuItem.
 */
@Entity
@Table(name = "menu_item")
public class MenuItem implements Serializable {

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
    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "price_dollor")
    private Double priceDollor;

    @Column(name = "price_euro")
    private Double priceEuro;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private FoodType type;

    @Lob
    @Column(name = "pic_jpg")
    private byte[] picJpg;

    @Column(name = "pic_jpg_content_type")
    private String picJpgContentType;

    @Lob
    @Column(name = "pic_png")
    private byte[] picPng;

    @Column(name = "pic_png_content_type")
    private String picPngContentType;

    @OneToOne(mappedBy = "menuItem")
    @JsonIgnore
    private OrderItem orderItem;

    @ManyToOne
    @JsonIgnoreProperties(value = "items", allowSetters = true)
    private Pizzaria pizzaria;

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

    public MenuItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public MenuItem description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredient() {
        return ingredient;
    }

    public MenuItem ingredient(String ingredient) {
        this.ingredient = ingredient;
        return this;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Double getPriceDollor() {
        return priceDollor;
    }

    public MenuItem priceDollor(Double priceDollor) {
        this.priceDollor = priceDollor;
        return this;
    }

    public void setPriceDollor(Double priceDollor) {
        this.priceDollor = priceDollor;
    }

    public Double getPriceEuro() {
        return priceEuro;
    }

    public MenuItem priceEuro(Double priceEuro) {
        this.priceEuro = priceEuro;
        return this;
    }

    public void setPriceEuro(Double priceEuro) {
        this.priceEuro = priceEuro;
    }

    public FoodType getType() {
        return type;
    }

    public MenuItem type(FoodType type) {
        this.type = type;
        return this;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public byte[] getPicJpg() {
        return picJpg;
    }

    public MenuItem picJpg(byte[] picJpg) {
        this.picJpg = picJpg;
        return this;
    }

    public void setPicJpg(byte[] picJpg) {
        this.picJpg = picJpg;
    }

    public String getPicJpgContentType() {
        return picJpgContentType;
    }

    public MenuItem picJpgContentType(String picJpgContentType) {
        this.picJpgContentType = picJpgContentType;
        return this;
    }

    public void setPicJpgContentType(String picJpgContentType) {
        this.picJpgContentType = picJpgContentType;
    }

    public byte[] getPicPng() {
        return picPng;
    }

    public MenuItem picPng(byte[] picPng) {
        this.picPng = picPng;
        return this;
    }

    public void setPicPng(byte[] picPng) {
        this.picPng = picPng;
    }

    public String getPicPngContentType() {
        return picPngContentType;
    }

    public MenuItem picPngContentType(String picPngContentType) {
        this.picPngContentType = picPngContentType;
        return this;
    }

    public void setPicPngContentType(String picPngContentType) {
        this.picPngContentType = picPngContentType;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public MenuItem orderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
        return this;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Pizzaria getPizzaria() {
        return pizzaria;
    }

    public MenuItem pizzaria(Pizzaria pizzaria) {
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
        if (!(o instanceof MenuItem)) {
            return false;
        }
        return id != null && id.equals(((MenuItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuItem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", ingredient='" + getIngredient() + "'" +
            ", priceDollor=" + getPriceDollor() +
            ", priceEuro=" + getPriceEuro() +
            ", type='" + getType() + "'" +
            ", picJpg='" + getPicJpg() + "'" +
            ", picJpgContentType='" + getPicJpgContentType() + "'" +
            ", picPng='" + getPicPng() + "'" +
            ", picPngContentType='" + getPicPngContentType() + "'" +
            "}";
    }
}
