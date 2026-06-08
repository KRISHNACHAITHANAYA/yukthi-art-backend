package com.yukthiartful.yukthi_artful_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.yukthiartful.yukthi_artful_backend.entity.Cart;
import com.yukthiartful.yukthi_artful_backend.entity.CartItem;
import com.yukthiartful.yukthi_artful_backend.entity.Order;
import com.yukthiartful.yukthi_artful_backend.entity.OrderItem;
import com.yukthiartful.yukthi_artful_backend.entity.User;
import com.yukthiartful.yukthi_artful_backend.exception.ResourceNotFoundException;
import com.yukthiartful.yukthi_artful_backend.repository.CartItemRepository;
import com.yukthiartful.yukthi_artful_backend.repository.CartRepository;
import com.yukthiartful.yukthi_artful_backend.repository.OrderItemRepository;
import com.yukthiartful.yukthi_artful_backend.repository.OrderRepository;
import com.yukthiartful.yukthi_artful_backend.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            UserRepository userRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order placeOrder() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());

        double totalAmount = 0;

        Order savedOrder = orderRepository.save(order);

        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(savedOrder);
            orderItem.setArtwork(cartItem.getArtwork());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getArtwork().getPrice());

            orderItemRepository.save(orderItem);

            totalAmount +=
                    cartItem.getArtwork().getPrice()
                    * cartItem.getQuantity();
        }

        savedOrder.setTotalAmount(totalAmount);

        orderRepository.save(savedOrder);

        return savedOrder;
    }

    @Override
    public List<Order> getMyOrders() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return orderRepository.findByUser(user);
    }

    @Override
    public Order getOrderById(Long orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));
    }
}