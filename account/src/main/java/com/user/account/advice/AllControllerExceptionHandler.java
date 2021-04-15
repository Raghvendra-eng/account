package com.user.account.advice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.user.account.message.DefaultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class AllControllerExceptionHandler {

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<String> amountNotNumber(){
        return new ResponseEntity<>(DefaultMessage.TRANSACTION_FAILED_INVALID_AMOUNT, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<String> validationErrorFound(){
        return new ResponseEntity<>(DefaultMessage.VALIDATION_FAILED, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CustomExceptions.class)
    public ResponseEntity<String> userNotFoundException(CustomExceptions c){
        return new ResponseEntity<>(c.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidFormatException.class)
    public ResponseEntity<String> invalidJSON(){
        return new ResponseEntity<>(DefaultMessage.JSON_PARSE_EXCEPTION, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JsonParseException.class)
    public ResponseEntity<String> invalidParseJSON(){
        return new ResponseEntity<>(DefaultMessage.JSON_PARSE_EXCEPTION, HttpStatus.BAD_REQUEST);
    }

}
