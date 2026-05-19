package com.example.springboot10th.domain.auth.service;

import com.example.springboot10th.domain.auth.converter.AuthConverter;
import com.example.springboot10th.domain.auth.dto.AuthRequestDTO;
import com.example.springboot10th.domain.auth.dto.AuthResponseDTO;
import com.example.springboot10th.domain.user.entity.User;
import com.example.springboot10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public AuthResponseDTO.SignupResponse signup(AuthRequestDTO.SignupRequest request) {
        User user = AuthConverter.toUser(request);
        User savedUser = userRepository.save(user);
        return AuthConverter.toSignupResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponseDTO.LoginResponse login(AuthRequestDTO.LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        return AuthConverter.toLoginResponse(user);
    }

    @Override
    public AuthResponseDTO.LogoutResponse logout() {
        return AuthConverter.toLogoutResponse();
    }
}
