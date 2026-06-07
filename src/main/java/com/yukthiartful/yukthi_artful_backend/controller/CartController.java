package com.yukthiartful.yukthi_artful_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukthiartful.yukthi_artful_backend.dto.AddToCartRequest;
import com.yukthiartful.yukthi_artful_backend.entity.Cart;
import com.yukthiartful.yukthi_artful_backend.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addToCart(
            @RequestBody AddToCartRequest request) {

        return cartService.addToCart(request);
    }

    @GetMapping
    public Cart getCart() {
        return cartService.getCart();
    }
}