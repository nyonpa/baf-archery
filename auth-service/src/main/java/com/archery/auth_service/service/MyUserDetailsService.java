package com.archery.auth_service.service;

import com.archery.auth_service.model.User;
import com.archery.auth_service.repository.UserRepository;
import com.archery.auth_service.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String cid) throws UsernameNotFoundException {
        System.out.println("USERNAME RECEIVED = [" +cid+ "]");

        Optional<User> user = userRepo.findByCid(cid);
        if (user.isEmpty())
        {
            System.out.println("user not found");
            throw new UsernameNotFoundException("User Not found with Cid"+ cid);
        }
        return new UserPrincipal(user.orElse(null));
    }
}
