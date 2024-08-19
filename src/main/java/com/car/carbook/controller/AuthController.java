package com.car.carbook.controller;


import com.car.carbook.config.Utils.JwtTokenUtil;
import com.car.carbook.model.request.UserRegistrationRequest;
import com.car.carbook.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserService userService;


    private final AuthenticationManager authenticationManager;


    private final JwtTokenUtil JwtTokenUtil;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil JwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.JwtTokenUtil = JwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        userService.registerUser(request);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtTokenUtil.generateToken(username);

        return ResponseEntity.ok("Bearer " + token);
    }
}
