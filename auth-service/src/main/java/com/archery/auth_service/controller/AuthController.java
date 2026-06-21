package com.archery.auth_service.controller;


import com.archery.auth_service.dto.LoginRequest;
import com.archery.auth_service.dto.RegisterRequest;
import com.archery.auth_service.response.AuthResponse;
import com.archery.auth_service.response.UserResponse;
import com.archery.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {

           return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
    }
    @PostMapping(value = "/login", produces = "application/json" )
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest);
        System.out.println(authResponse);
        return ResponseEntity.ok(authResponse);

    }
    @GetMapping("/users/{cid}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String cid) {
        return ResponseEntity.ok(authService.getUser(cid));
    }
    //activate account
    @PutMapping("/activate/{cid}")
    public ResponseEntity<String>  activate(@PathVariable String cid) {
        authService.activateUser(cid);
        return ResponseEntity.ok("Activated");
    }

}
