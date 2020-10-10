package com.restaurant.tablebookingservice.dto.exceptions;

import org.springframework.http.HttpStatus;

public class ReservationException extends RuntimeException{
    private HttpStatus httpStatus;

    public ReservationException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
