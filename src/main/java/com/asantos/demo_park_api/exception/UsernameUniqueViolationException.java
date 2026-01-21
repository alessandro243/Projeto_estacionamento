package com.asantos.demo_park_api.exception;

public class UsernameUniqueViolationException extends RuntimeException{
    public UsernameUniqueViolationException(String msg){
        super(msg);
    }
}
