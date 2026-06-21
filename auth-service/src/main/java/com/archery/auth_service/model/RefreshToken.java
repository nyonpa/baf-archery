package com.archery.auth_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false, length = 500)
    private String token;
    @Column(nullable = false)
    private String cid;
    @Column(nullable = false)
    private LocalDateTime expireDate;
}
