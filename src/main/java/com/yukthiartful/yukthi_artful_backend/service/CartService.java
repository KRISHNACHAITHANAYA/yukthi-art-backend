package com.yukthiartful.yukthi_artful_backend.service;

import com.yukthiartful.yukthi_artful_backend.dto.AddToCartRequest;
import com.yukthiartful.yukthi_artful_backend.entity.Cart;

public interface CartService {

    Cart addToCart(AddToCartRequest request);

    Cart getCart();

    void removeItem(Long itemId);
}