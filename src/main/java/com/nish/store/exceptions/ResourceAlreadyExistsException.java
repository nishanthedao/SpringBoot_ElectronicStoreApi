package com.nish.store.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException() {
        super("Resource not found !!");
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
