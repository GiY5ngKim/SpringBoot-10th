package com.example.springboot10th.domain.auth.converter;

import com.example.springboot10th.domain.auth.dto.AuthRequestDTO;
import com.example.springboot10th.domain.auth.dto.AuthResponseDTO;
import com.example.springboot10th.domain.user.entity.User;

public class AuthConverter {

    public static User toUser(AuthRequestDTO.SignupRequest request) {
        return User.builder()
                .email(request.getEmail())
                .name(request.getName() != null ? request.getName() : "사용자")
                .nickname(request.getName() != null ? request.getName() : "사용자")
                .phoneNum("010-0000-0000")
                .address(request.getAddress())
                .point(0)
                .build();
    }

    public static AuthResponseDTO.SignupResponse toSignupResponse(User user) {
        return AuthResponseDTO.SignupResponse.builder()
                .memberId(user.getId())
                .build();
    }

    public static AuthResponseDTO.LoginResponse toLoginResponse(User user) {
        return AuthResponseDTO.LoginResponse.builder()
                .memberId(user.getId())
                .build();
    }

    public static AuthResponseDTO.LogoutResponse toLogoutResponse() {
        return AuthResponseDTO.LogoutResponse.builder()
                .memberId(1L)
                .build();
    }
}
