package com.example.airlines.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Item not found")
public class IdSearchException extends RuntimeException {

    public IdSearchException(String message){
        super(message);
    }
}
