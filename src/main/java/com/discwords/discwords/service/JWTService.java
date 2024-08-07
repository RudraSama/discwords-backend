package com.discwords.discwords.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    @Value("${jwt_secret_key}")
    private String secretKey;

    public String generateString(String email, String id){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", email);
        claims.put("id", id);

        return createToken(claims);
    }


    public String createToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7776000000L)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }


    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
