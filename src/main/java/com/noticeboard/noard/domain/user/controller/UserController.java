package com.noticeboard.noard.domain.user.controller;

import com.noticeboard.noard.common.session.CookieManager;
import com.noticeboard.noard.common.session.SessionManager;
import com.noticeboard.noard.domain.user.dto.LoginRequest;
import com.noticeboard.noard.domain.user.dto.LoginResponse;
import com.noticeboard.noard.domain.user.dto.SignUpRequest;
import com.noticeboard.noard.domain.user.dto.SignUpResponse;
import com.noticeboard.noard.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final String SIGNUP_SUCCESS_MESSAGE = "회원가입이 성공적으로 완료되었습니다.";

    private final String LOGIN_SUCCESS_MESSAGE = "로그인이 성공적으로 완료되었습니다.";

    private final String SET_COOKIE = "Set-Cookie";

    @PostMapping("/api/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request) {
        userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpResponse(SIGNUP_SUCCESS_MESSAGE));
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpSession session) {
        userService.login(request);
        String sessionId = SessionManager.createSession(session, request.getEmail());
        return ResponseEntity.ok().header(SET_COOKIE, CookieManager.generateCookie(sessionId).toString()).body(new LoginResponse(LOGIN_SUCCESS_MESSAGE));
    }
}
