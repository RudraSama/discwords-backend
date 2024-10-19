package com.discwords.discwords.service;
import com.discwords.discwords.exceptions.JWTMalformed;
import com.discwords.discwords.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    @Value("${jwt_secret_key}")
    private String secretKey;

    private final Logger LOGGER = LogManager.getLogger();

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
       return validJwtToken(token)?getAllClaims(token).get("email"):null;
    }

    public Object extractUserId(String token){
        return validJwtToken(token)?getAllClaims(token).get("id"):null;
    }

    public Object extractExpiryDate(String token){
        return validJwtToken(token)?getAllClaims(token).getExpiration():null;
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
        return expiryDate.after(new Date());
    }

    public boolean isTokenValid(String token, User user) {
        final String username = extractEmail(token).toString();
        return (username.equals(user.getEmail()) && isTokenExpired(token));
    }

    public boolean validJwtToken(String token){
        try{
            Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secretKey))).parseClaimsJws(token);
            return true;
        }
        catch (MalformedJwtException exception){
            throw new JWTMalformed();
        }

    }

    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
