package com.yukthiartful.yukthi_artful_backend.service;

import java.util.List;

import com.yukthiartful.yukthi_artful_backend.entity.Order;

public interface OrderService {

    Order placeOrder();

    List<Order> getMyOrders();

    Order getOrderById(Long orderId);
    Order updateOrderStatus(Long orderId, String status);
}