package com.discwords.discwords.exceptions;

import io.jsonwebtoken.MalformedJwtException;

public class JWTMalformed extends MalformedJwtException {
    public JWTMalformed(){
        super("JWT Malformed Exception Occurred");
    }
}
