package com.discwords.discwords.service;
import com.discwords.discwords.model.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwt_secret_key}")
    private String secretKey;

    public String generateToken(String email, String id){
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("id", id);

        return createToken(claims);
    }


    public String createToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject("gaurav")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7776000000L))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    public Object extractEmail(String token){
       return getAllClaims(token).get("email");
    }

    public Claims getAllClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean isTokenExpired(String token){
        Date expiryDate = getAllClaims(token).getExpiration();
        System.out.println(expiryDate);
        System.out.println(new Date());
        return expiryDate.after(new Date());
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractEmail(token).toString();
        return (username.equals(userDetails.getEmail()) && isTokenExpired(token));
    }


    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
