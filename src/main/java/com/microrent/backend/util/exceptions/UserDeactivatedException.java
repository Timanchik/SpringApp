package com.microrent.backend.util.exceptions;

public class UserDeactivatedException extends RuntimeException{
    public UserDeactivatedException(String msg) {
        super(msg);
    }
}
