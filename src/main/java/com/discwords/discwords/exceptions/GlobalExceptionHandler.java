package com.discwords.discwords.exceptions;

import com.discwords.discwords.DTOs.ErrorResponseDTO;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleJWTMalformedExecption(MalformedJwtException exception){

        LOGGER.error("Exception Occurred : ", exception);

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(500, exception.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
