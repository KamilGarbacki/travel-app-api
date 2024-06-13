package com.kamilgarbacki.Travel_app.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{


    public ResourceNotFound(String message) {
        super(message);
    }
}
