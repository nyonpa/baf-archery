package com.archery.auth_service.response;

import com.archery.auth_service.model.Role;
import com.archery.auth_service.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class UserResponse {
    private String cid;
    private boolean active;
    private Set<Role> roles;

    public UserResponse(User user) {
        this.cid = user.getCid();
        this.active = user.isActive();
        this.roles = user.getRoles();
    }
}