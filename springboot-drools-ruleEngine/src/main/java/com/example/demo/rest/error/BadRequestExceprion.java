package com.example.demo.rest.error;

/**
 * Created by radugrig on 29/05/2018.
 */
public class BadRequestExceprion extends RuntimeException {

    public BadRequestExceprion(String msg){
        super(msg);
    }
}
