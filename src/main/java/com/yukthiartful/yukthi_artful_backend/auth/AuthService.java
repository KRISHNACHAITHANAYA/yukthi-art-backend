package com.yukthiartful.yukthi_artful_backend.auth;

import com.yukthiartful.yukthi_artful_backend.dto.LoginRequest;
import com.yukthiartful.yukthi_artful_backend.dto.LoginResponse;
import com.yukthiartful.yukthi_artful_backend.dto.RegisterRequest;
import com.yukthiartful.yukthi_artful_backend.entity.User;

public interface AuthService {

    User register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}