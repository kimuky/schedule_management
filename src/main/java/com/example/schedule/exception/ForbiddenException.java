package com.example.schedule.exception;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException() {
        super("해당 유저가 아님");
    }
}
