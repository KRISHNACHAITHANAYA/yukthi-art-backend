package com.yukthiartful.yukthi_artful_backend.dto;

public class DashboardStatsResponse {

    private Long totalUsers;
    private Long totalCategories;
    private Long totalArtworks;
    private Long totalOrders;

    public DashboardStatsResponse(
            Long totalUsers,
            Long totalCategories,
            Long totalArtworks,
            Long totalOrders) {

        this.totalUsers = totalUsers;
        this.totalCategories = totalCategories;
        this.totalArtworks = totalArtworks;
        this.totalOrders = totalOrders;
    }

    public Long getTotalUsers() {
        return totalUsers;
    }

    public Long getTotalCategories() {
        return totalCategories;
    }

    public Long getTotalArtworks() {
        return totalArtworks;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }
}