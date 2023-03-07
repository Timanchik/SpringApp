package com.microrent.backend.controllers;

import com.microrent.backend.services.UserService;
import com.microrent.backend.util.CookieManager;
import com.microrent.backend.util.exceptions.UserDeactivatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.microrent.backend.dto.AuthenticationDTO;
import com.microrent.backend.security.JWTUtil;
import com.microrent.backend.util.ResponseMessage;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response){
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword());
        try{
            authenticationManager.authenticate(auth);
        } catch (UsernameNotFoundException | UserDeactivatedException e){
            return ResponseEntity.ok(new ResponseMessage(false, e.getMessage()));
        }
        String token = jwtUtil.generateToken(authenticationDTO.getEmail());
        userService.updateVisitDate(authenticationDTO.getEmail());
        response.addCookie(CookieManager.createJwtCookie(token));
        return ResponseEntity.ok(new ResponseMessage(true, "Успешная авторизация!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseMessage> logout(HttpServletResponse response){
        response.addCookie(CookieManager.deleteCookie());
        return ResponseEntity.ok(new ResponseMessage(true, "Logout successful!"));
    }

}
