package com.yukthiartful.yukthi_artful_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yukthiartful.yukthi_artful_backend.entity.Order;
import com.yukthiartful.yukthi_artful_backend.entity.User;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}