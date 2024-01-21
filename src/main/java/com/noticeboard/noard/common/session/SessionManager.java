package com.noticeboard.noard.common.session;

// 1. 로그인 인증 성공 시 무작위 세션 값 생성
// 2. inmemory 방식으로 세션 관리
// 3. 세션을 쿠키를 통해 클라이언트로 전송

//

import jakarta.servlet.http.HttpSession;

import java.util.UUID;

public class SessionManager {

    // 무작위 해쉬 값 생성 메서드
    private static String generateRandomSessionId() {
        return UUID.randomUUID().toString();
    }

    // 세션에 추가하는 메서드
    public static String createSession(HttpSession session, String email) {
        String sessionId = generateRandomSessionId();
        session.setAttribute(sessionId, email);
        return sessionId;
    }


    // 세션에서 삭제하는 메서드
    public static void deleteSession(HttpSession session, String sessionId) {
        session.removeAttribute(sessionId);
    }

    // 해시 값을 통해 세션이 존재하는지 검증
}
