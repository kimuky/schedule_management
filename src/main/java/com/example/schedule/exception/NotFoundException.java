package com.example.schedule.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("없는 정보");
    }
}