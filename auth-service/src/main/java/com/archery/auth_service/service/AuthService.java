package com.archery.auth_service.service;


import com.archery.auth_service.model.RefreshToken;
import com.archery.auth_service.response.AuthResponse;
import com.archery.auth_service.dto.LoginRequest;
import com.archery.auth_service.dto.RegisterRequest;
import com.archery.auth_service.model.Role;
import com.archery.auth_service.model.User;
import com.archery.auth_service.repository.UserRepository;
import com.archery.auth_service.response.UserResponse;
import com.archery.auth_service.security.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository repo;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository repo,
                       AuthenticationManager authenticationManager, JWTService jwtService, RefreshTokenService refreshTokenService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.repo = repo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    public String register(RegisterRequest req) {

        User user = new User();
        user.setCid(req.getCid());
        user.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));
        user.setRoles(new HashSet<>(Set.of(Role.USER)));
        user.setActive(false); // activation required
        repo.save(user);
        return "User registered. Please activate account.";
    }

    public AuthResponse login(LoginRequest loginRequest) {

        System.out.println("Login Request: " + loginRequest);
        System.out.println("Step 1: Before Authentication");
        System.out.println("Step 2: cid"+ loginRequest.getCid());
        System.out.println("Step 3: password"+ loginRequest.getPassword());
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                                loginRequest.getCid(),
                            loginRequest.getPassword()
                ));
        System.out.println("Step 2: After Authentication");
        User user = repo.findByCid(loginRequest.getCid())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(! user.isActive())
        {
            throw new RuntimeException("User is not active.");
        }
        UserDetails userDetails = new UserPrincipal(user);
        String token = jwtService.generateToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(String.valueOf(loginRequest.getCid()));
        AuthResponse authResponse = new AuthResponse(token,refreshToken.getToken());
        System.out.println("Auth Response: " + authResponse);
        return authResponse;



    }
    // GET USER
    public UserResponse getUser(String userCid) {
        User user = repo.findByCid(userCid).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user);
    }

    // Activate User
    public void activateUser(String userCid) {
        User user = repo.findByCid(userCid).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(true);
        repo.save(user);
    }

    // assign roles
    public void grantRole(String userCid, Role role) {
        User user = repo.findByCid(userCid).orElseThrow(() -> new RuntimeException("User not found"));
        user.getRoles().add(role);
        repo.save(user);
    }
    //revoke user role
    public void revokeRole(String userCid, Role role) {
        User user = repo.findByCid(userCid).orElseThrow(() -> new RuntimeException("User not found"));
        user.getRoles().remove(role);
        repo.save(user);
    }
    public void deactivateUser(String userCid) {
        User user = repo.findByCid(userCid).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        repo.save(user);
    }

    // Refresh
    public AuthResponse refreshToken(String refreshToken) {
        RefreshToken refreshTokenObj = refreshTokenService.validateRefreshToken(refreshToken);
        User user = repo.findByCid(refreshTokenObj.getCid()).orElseThrow(() -> new RuntimeException("User not found"));
        String newAccessToken = jwtService.generateToken(new UserPrincipal(user));
        return new  AuthResponse(newAccessToken,refreshTokenObj.getToken());
    }
    //logout
    public String logout(String refreshToken) {

        RefreshToken token = refreshTokenService.validateRefreshToken(refreshToken);

        refreshTokenService.deleteRefreshToken(token);

        return "Logged out successfully";
    }



}
