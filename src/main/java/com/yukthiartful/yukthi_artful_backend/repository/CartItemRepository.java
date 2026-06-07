package com.yukthiartful.yukthi_artful_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yukthiartful.yukthi_artful_backend.entity.CartItem;

public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {

}