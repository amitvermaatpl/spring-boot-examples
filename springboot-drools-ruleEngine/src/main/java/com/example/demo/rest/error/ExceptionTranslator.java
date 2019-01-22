package com.example.demo.rest.error;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by radugrig on 29/05/2018.
 */

@ControllerAdvice
public class ExceptionTranslator {


    @ExceptionHandler(BadRequestExceprion.class)
    public ResponseEntity<String> handleBadRequest(BadRequestExceprion ex, NativeWebRequest request){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
