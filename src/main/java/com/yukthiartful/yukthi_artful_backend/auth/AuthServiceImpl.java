package com.yukthiartful.yukthi_artful_backend.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yukthiartful.yukthi_artful_backend.dto.LoginRequest;
import com.yukthiartful.yukthi_artful_backend.dto.LoginResponse;
import com.yukthiartful.yukthi_artful_backend.dto.RegisterRequest;
import com.yukthiartful.yukthi_artful_backend.entity.User;
import com.yukthiartful.yukthi_artful_backend.exception.EmailAlreadyExistsException;
import com.yukthiartful.yukthi_artful_backend.exception.ResourceNotFoundException;
import com.yukthiartful.yukthi_artful_backend.repository.UserRepository;
import com.yukthiartful.yukthi_artful_backend.security.JwtUtil;
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
    }

}