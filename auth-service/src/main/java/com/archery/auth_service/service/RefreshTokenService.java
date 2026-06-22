package com.archery.auth_service.service;

import com.archery.auth_service.model.RefreshToken;
import com.archery.auth_service.repository.RefreshTokenRepository;
import jakarta.persistence.Column;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class RefreshTokenService
{
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository)
    {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken generateRefreshToken(String cid)
    {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCid(cid);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(LocalDateTime.now().plusDays(7));
        return refreshTokenRepository.save(refreshToken);

    }

    public RefreshToken validateRefreshToken(String token)
    {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> new RuntimeException("Invalid Refresh Token"));

        if(refreshToken.getExpireDate().isBefore(LocalDateTime.now()))
        {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Invalid Refresh Token expired");
        }
        return refreshToken;
    }
    public void deleteRefreshToken(RefreshToken token)
    {   String cid = token.getCid();
        System.out.println("Deleting Refresh Token: " + cid);
        refreshTokenRepository.delete(token);
    }
}
