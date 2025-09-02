package com.guts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotExistException extends RuntimeException{

    public ResourceNotExistException(String message, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", message, fieldName, fieldValue));
    }

}
