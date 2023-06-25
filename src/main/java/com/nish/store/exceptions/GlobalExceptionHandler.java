package com.nish.store.exceptions;

import com.nish.store.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // Handle resource not found exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        logger.info("Exception handler invoked");
        ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder()
                .message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
        return new ResponseEntity(apiResponseMessage, HttpStatus.NOT_FOUND);
    }

    // Handle resource already exist exception
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponseMessage> resourceAlreadyExistExceptionHandler(ResourceAlreadyExistsException ex){
        logger.info("Exception handler invoked");
        ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.CONFLICT).success(true).build();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.CONFLICT);
    }

    // For Method argument not valid exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();
        Map<String, Object> response = new HashMap<>();
        objectErrorList.stream().forEach(objectError -> {
            String message = objectError.getDefaultMessage();
            String field = ((FieldError)objectError).getField();
            response.put(field, message);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle Bad api request exception
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponseMessage> badApiRequestExceptionHandler(BadApiRequest ex){
        logger.info("Bad Api Exception handler invoked");
        ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder()
                .message(ex.getMessage()).success(true).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity(apiResponseMessage, HttpStatus.BAD_REQUEST);
    }
}
