package com.example.dream_stadium_V2.common.auth.cookie;

import jakarta.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie createCookie(String accessToken) {

        Cookie cookie = new Cookie("accessToken", accessToken);

        cookie.setPath("/");
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);

        return cookie;

    }
}
