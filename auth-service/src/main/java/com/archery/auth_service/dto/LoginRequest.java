package com.archery.auth_service.dto;


import lombok.Data;

@Data
public class LoginRequest {
    private String cid;
    private String password;
}
