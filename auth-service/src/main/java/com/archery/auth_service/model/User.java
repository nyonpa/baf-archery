package com.archery.auth_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.archery.auth_service.model.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(unique=true, nullable=false)
    private String cid;
    @Column(nullable=false)
    private String password;
    private boolean active = false;
    private int failedLoginAttempts = 0;
    private LocalDateTime lockUntil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles =new HashSet<>();

}
