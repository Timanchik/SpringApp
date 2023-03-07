package com.microrent.backend.configs;

import com.microrent.backend.util.CookieManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
import com.microrent.backend.security.JWTUtil;
import com.microrent.backend.services.JwtUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    public JWTFilter(JWTUtil jwtUtil, JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie cookie = WebUtils.getCookie(request, "jwt-token");
        if (cookie != null) {
            String jwt = cookie.getValue();
            if (jwtUtil.validateToken(jwt)) {
                String email = jwtUtil.retrieveClaim(jwt);
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                userDetails.getPassword(),
                                userDetails.getAuthorities());
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            else {
                response.addCookie(CookieManager.deleteCookie());
            }
        }

        filterChain.doFilter(request, response);
    }
}
