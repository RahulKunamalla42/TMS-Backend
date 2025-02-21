package com.backend.user_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    private final int VALIDITY=18000000;
    private final String SECRETE="WyNQvZSFOWjdmmv1ccSlhRFz7bEK3OQA/Lfo9Yc9KqfkagV2X455S3E82gq8F5HZKl+V36ZGYprG1QZaWo2cNQ==";

    public String GenarateToken(UserDetails userDetails){
        return Jwts
                .builder()
                .subject(userDetails.getUsername())
                .signWith(key())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .compact();
    }
    public SecretKey key(){
        byte[] decode = Base64.getDecoder().decode(SECRETE);
        return Keys.hmacShaKeyFor(decode);
    }
    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractusername(String token){
        return getClaims(token).getSubject();
    }
    public boolean isValid(String token,UserDetails userDetails){
        return getClaims(token).getSubject().equals(userDetails.getUsername()) && !isExpired(token);
    }
    public boolean isExpired(String token){
        return getClaims(token).getExpiration().before(Date.from(Instant.now()));
    }
}
