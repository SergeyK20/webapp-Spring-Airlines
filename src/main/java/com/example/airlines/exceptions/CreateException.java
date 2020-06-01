package com.example.airlines.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_MODIFIED, reason = "Element did not create")
public class CreateException extends RuntimeException {
    public CreateException(String message){
        super(message);
    }
}
