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

    @ExceptionHandler(JWTMalformed.class)
    public ResponseEntity<ErrorResponseDTO> handleJWTMalformedException(JWTMalformed exception){

        LOGGER.error("Exception Occurred : ", exception);

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(500, exception.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<ErrorResponseDTO> handleUsernameAlreadyExistsException(UsernameAlreadyExists exception){
        LOGGER.error("Exception Occurred: {}", exception.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(409, exception.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExists exception){
        LOGGER.error("Exception Occurred : {}", exception.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(409, exception.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }
}
