package com.guts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message, String fieldName, String fieldValue) {
        super(String.format("%s already exists with %s : '%s'", message, fieldName, fieldValue));
    }

}
