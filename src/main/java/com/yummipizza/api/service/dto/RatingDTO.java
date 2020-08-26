package com.yummipizza.api.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.yummipizza.api.domain.Rating} entity.
 */
public class RatingDTO implements Serializable {
    
    private Long id;

    private Long customerId;

    private Long menuItemId;

    private Integer rating;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RatingDTO)) {
            return false;
        }

        return id != null && id.equals(((RatingDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RatingDTO{" +
            "id=" + getId() +
            ", customerId=" + getCustomerId() +
            ", menuItemId=" + getMenuItemId() +
            ", rating=" + getRating() +
            "}";
    }
}
