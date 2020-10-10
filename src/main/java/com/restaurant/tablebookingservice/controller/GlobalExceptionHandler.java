package com.restaurant.tablebookingservice.controller;

import com.restaurant.tablebookingservice.dto.ErrorResponse;
import com.restaurant.tablebookingservice.dto.exceptions.ReservationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<ErrorResponse> handle(ReservationException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorResponse(e.getMessage()));
    }
}
