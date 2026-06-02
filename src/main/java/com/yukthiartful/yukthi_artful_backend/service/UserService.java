package com.yukthiartful.yukthi_artful_backend.service;

import java.util.List;

import com.yukthiartful.yukthi_artful_backend.entity.User;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();
} 