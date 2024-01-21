package com.noticeboard.noard.domain.user.service;

import com.noticeboard.noard.domain.user.dto.LoginRequest;
import com.noticeboard.noard.domain.user.dto.SignUpRequest;
import com.noticeboard.noard.domain.user.entity.User;
import com.noticeboard.noard.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public void save(SignUpRequest request) {
        User user = User.builder()
                        .nickname(request.getNickname())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .build();

        userRepository.save(user);
    }

    public void login(LoginRequest request) {
        userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException(request.getEmail()));
    }
}
