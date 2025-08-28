package com.example.creditcard.exception;

public class LimitExceededException extends RuntimeException{

    public LimitExceededException(String message){
        super(message);
    }
}
