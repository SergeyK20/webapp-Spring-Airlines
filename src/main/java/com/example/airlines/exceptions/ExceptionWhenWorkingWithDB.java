package com.example.airlines.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_MODIFIED, reason = "Error  when working with database")
public class ExceptionWhenWorkingWithDB extends RuntimeException {
    public ExceptionWhenWorkingWithDB(String message){
        super(message);
    }
}
