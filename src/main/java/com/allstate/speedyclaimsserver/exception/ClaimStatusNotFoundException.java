package com.allstate.speedyclaimsserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClaimStatusNotFoundException extends RuntimeException {

    public ClaimStatusNotFoundException(String message){
        super(message);
    }
}