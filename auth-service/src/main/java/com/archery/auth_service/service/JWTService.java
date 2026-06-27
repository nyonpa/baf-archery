package com.archery.auth_service.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;


@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getKey() {
        byte[] encodedKey = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(encodedKey);
    }

    // generating a security key
    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            String aa = Base64.getEncoder().encodeToString(sk.getEncoded());
            System.out.println(aa);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(UserDetails userDetails)
    {
            List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            return Jwts.builder()
                    .subject(userDetails.getUsername())
                    .claim("cid", userDetails.getUsername())
                    .claim("roles",roles)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis()+ 1000*60*15))
                    .signWith(getKey())
                    .compact();



    }

    public String extractUserName(String token) {

        return extractClaim(token, Claims::getSubject);
    }
    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token) {
        final String username = extractUserName(token);
        try{
            Jwts.parser()
                    .verifyWith(getKey())
                    .build().parseSignedClaims(token);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
