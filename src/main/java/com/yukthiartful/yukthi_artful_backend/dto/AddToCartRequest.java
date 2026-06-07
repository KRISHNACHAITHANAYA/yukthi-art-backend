package com.yukthiartful.yukthi_artful_backend.dto;

public class AddToCartRequest {

    private Long artworkId;
    private Integer quantity;

    public Long getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(Long artworkId) {
        this.artworkId = artworkId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}