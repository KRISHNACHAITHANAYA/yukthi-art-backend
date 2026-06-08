package com.yukthiartful.yukthi_artful_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yukthiartful.yukthi_artful_backend.entity.OrderItem;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {

}