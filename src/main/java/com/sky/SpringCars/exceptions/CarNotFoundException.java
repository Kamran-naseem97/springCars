package com.sky.SpringCars.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Car found with that id")
public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException() {
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}
