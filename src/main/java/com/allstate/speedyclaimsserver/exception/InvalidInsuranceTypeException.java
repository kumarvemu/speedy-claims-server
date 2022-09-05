package com.allstate.speedyclaimsserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidInsuranceTypeException extends RuntimeException{

    public InvalidInsuranceTypeException(String message){
        super(message);
    }
}
