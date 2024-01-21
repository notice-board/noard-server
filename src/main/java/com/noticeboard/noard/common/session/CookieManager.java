package com.noticeboard.noard.common.session;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

public class CookieManager {

    public static ResponseCookie generateCookie(String token) {
        return ResponseCookie.from("session-token", token)
                .httpOnly(true)
                .sameSite("None")
                .build();
    }
}
