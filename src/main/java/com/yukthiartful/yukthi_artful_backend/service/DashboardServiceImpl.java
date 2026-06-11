package com.yukthiartful.yukthi_artful_backend.service;

import org.springframework.stereotype.Service;

import com.yukthiartful.yukthi_artful_backend.dto.DashboardStatsResponse;
import com.yukthiartful.yukthi_artful_backend.repository.ArtworkRepository;
import com.yukthiartful.yukthi_artful_backend.repository.CategoryRepository;
import com.yukthiartful.yukthi_artful_backend.repository.OrderRepository;
import com.yukthiartful.yukthi_artful_backend.repository.UserRepository;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ArtworkRepository artworkRepository;
    private final OrderRepository orderRepository;

    public DashboardServiceImpl(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ArtworkRepository artworkRepository,
            OrderRepository orderRepository) {

        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.artworkRepository = artworkRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public DashboardStatsResponse getStats() {

        return new DashboardStatsResponse(
                userRepository.count(),
                categoryRepository.count(),
                artworkRepository.count(),
                orderRepository.count());
    }
}