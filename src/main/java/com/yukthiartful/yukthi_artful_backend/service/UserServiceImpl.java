package com.yukthiartful.yukthi_artful_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yukthiartful.yukthi_artful_backend.entity.User;
import com.yukthiartful.yukthi_artful_backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}