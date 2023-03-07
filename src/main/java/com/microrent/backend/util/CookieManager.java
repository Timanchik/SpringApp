package com.microrent.backend.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class CookieManager {

    public static Cookie createJwtCookie(String token){
        Cookie cookie = new Cookie("jwt-token", token);
        cookie.setMaxAge(86400);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

    public static Cookie deleteCookie(){
        Cookie cookie = new Cookie("jwt-token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
