package com.example.schedule.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("빈값이 있음");
    }
}


