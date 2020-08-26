package com.yummipizza.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;
import com.yummipizza.api.domain.enumeration.FoodType;

/**
 * A DTO for the {@link com.yummipizza.api.domain.MenuItem} entity.
 */
public class MenuItemDTO implements Serializable {
    
    private Long id;

    private String name;

    @Lob
    private String description;

    @Lob
    private String ingredient;

    private Double priceDollor;

    private Double priceEuro;

    private FoodType type;

    @Lob
    private byte[] picJpg;

    private String picJpgContentType;
    @Lob
    private byte[] picPng;

    private String picPngContentType;

    private Long pizzariaId;
    
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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Double getPriceDollor() {
        return priceDollor;
    }

    public void setPriceDollor(Double priceDollor) {
        this.priceDollor = priceDollor;
    }

    public Double getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(Double priceEuro) {
        this.priceEuro = priceEuro;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public byte[] getPicJpg() {
        return picJpg;
    }

    public void setPicJpg(byte[] picJpg) {
        this.picJpg = picJpg;
    }

    public String getPicJpgContentType() {
        return picJpgContentType;
    }

    public void setPicJpgContentType(String picJpgContentType) {
        this.picJpgContentType = picJpgContentType;
    }

    public byte[] getPicPng() {
        return picPng;
    }

    public void setPicPng(byte[] picPng) {
        this.picPng = picPng;
    }

    public String getPicPngContentType() {
        return picPngContentType;
    }

    public void setPicPngContentType(String picPngContentType) {
        this.picPngContentType = picPngContentType;
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
        if (!(o instanceof MenuItemDTO)) {
            return false;
        }

        return id != null && id.equals(((MenuItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuItemDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", ingredient='" + getIngredient() + "'" +
            ", priceDollor=" + getPriceDollor() +
            ", priceEuro=" + getPriceEuro() +
            ", type='" + getType() + "'" +
            ", picJpg='" + getPicJpg() + "'" +
            ", picPng='" + getPicPng() + "'" +
            ", pizzariaId=" + getPizzariaId() +
            "}";
    }
}
