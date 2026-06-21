package com.archery.auth_service.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String cid;
    private String password;
}
