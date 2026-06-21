package com.archery.auth_service.controller;

import com.archery.auth_service.model.Role;
import com.archery.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AuthService authService;

    public AdminController(AuthService authService) {
        this.authService = authService;
    }
    @PutMapping("/promote/player/{cid}")
    public ResponseEntity<String> promotePlayer(@PathVariable String cid) {
        authService.grantRole(cid, Role.PLAYER);
        return ResponseEntity.ok("PLAYER role granted");
    }

    @PutMapping("/promote/captain/{cid}")
    public ResponseEntity<String> promoteCaptain(@PathVariable String cid) {
        authService.grantRole(cid, Role.TEAM_CAPTAIN);
        return ResponseEntity.ok("TEAM_CAPTAIN role granted");
    }

    @PutMapping("/promote/organizer/{cid}")
    public ResponseEntity<String> promoteOrganizer(@PathVariable String cid) {
        authService.grantRole(cid, Role.ORGANIZER);
        return ResponseEntity.ok("ORGANIZER role granted");
    }

    @PutMapping("/promote/admin/{cid}")
    public ResponseEntity<String> promoteAdmin(@PathVariable String cid) {
        authService.grantRole(cid, Role.ADMIN);
        return ResponseEntity.ok("ADMIN role granted");
    }

    @DeleteMapping("/users/{cid}")
    public ResponseEntity<String> deactivate(@PathVariable String cid) {
        authService.deactivateUser(cid);
        return ResponseEntity.ok("User deactivated");
    }
}