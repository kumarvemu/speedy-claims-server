package com.allstate.speedyclaimsserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InsuranceTypeNotFoundException extends RuntimeException{

    public InsuranceTypeNotFoundException(String message){
        super(message);
    }
}