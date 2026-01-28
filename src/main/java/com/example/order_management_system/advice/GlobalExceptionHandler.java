package com.example.order_management_system.advice;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<ApiError> ResourceNotFoundException(ResourceNotFound exception){
//        ApiError apiError=new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>( apiError,HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handelAuthenticationException(AuthenticationException ex){
        ApiError apiError=new ApiError(ex.getLocalizedMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handelJwtxception(JwtException ex){
        ApiError apiError=new ApiError(ex.getLocalizedMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

}
