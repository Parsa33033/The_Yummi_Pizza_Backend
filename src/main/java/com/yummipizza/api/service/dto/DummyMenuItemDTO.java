package com.yummipizza.api.service.dto;


public class DummyMenuItemDTO extends MenuItemDTO {

    public DummyMenuItemDTO() {
    }

    public DummyMenuItemDTO(MenuItemDTO menuItemDTO) {
        this.setId(menuItemDTO.getId());
        this.setDescription(menuItemDTO.getDescription());
        this.setIngredient(menuItemDTO.getIngredient());
        this.setName(menuItemDTO.getName());
        this.setPicJpg(menuItemDTO.getPicJpg());
        this.setPicJpgContentType(menuItemDTO.getPicJpgContentType());
        this.setPicPng(menuItemDTO.getPicPng());
        this.setPicPngContentType(menuItemDTO.getPicPngContentType());
        this.setPizzariaId(menuItemDTO.getPizzariaId());
        this.setPriceDollor(menuItemDTO.getPriceDollor());
        this.setPriceEuro(menuItemDTO.getPriceEuro());
        this.setType(menuItemDTO.getType());
    }
}
