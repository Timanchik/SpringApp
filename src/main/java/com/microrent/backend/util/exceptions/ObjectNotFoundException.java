package com.microrent.backend.util.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
