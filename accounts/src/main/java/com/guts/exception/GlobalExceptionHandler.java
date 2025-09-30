package com.guts.exception;

import com.guts.dto.ErrorMessageDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        errorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> handleGlobalExistException(Exception exception, WebRequest webRequest){
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setPath(webRequest.getDescription(false));
        errorMessageDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorMessageDTO.setMessage(exception.getMessage());
        errorMessageDTO.setTime(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessageDTO);
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorMessageDTO> handleCustomerAlreadyExistException(CustomerAlreadyExistException exception, WebRequest webRequest){
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setPath(webRequest.getDescription(false));
        errorMessageDTO.setStatus(HttpStatus.CONFLICT);
        errorMessageDTO.setMessage(exception.getMessage());
        errorMessageDTO.setTime(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessageDTO);
    }

    @ExceptionHandler(ResourceNotExistException.class)
    public ResponseEntity<ErrorMessageDTO> handleResourceNotExistException(ResourceNotExistException exception, WebRequest webRequest) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setPath(webRequest.getDescription(false));
        errorMessageDTO.setStatus(HttpStatus.NOT_FOUND);
        errorMessageDTO.setMessage(exception.getMessage());
        errorMessageDTO.setTime(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageDTO);
    }

}
