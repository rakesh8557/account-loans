package com.guts.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", message, fieldName, fieldValue));
    }
}
