package com.example.order_management_system.advice;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timeStamp;
    private String error;
    private HttpStatusCode httpStatusCode;

    public ApiError(){
        this.timeStamp=LocalDateTime.now();
    }
    public ApiError(String error, HttpStatusCode httpStatusCode){
        this();
        this.error=error;
        this.httpStatusCode=httpStatusCode;
    }
}
