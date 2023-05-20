package com.flightreservation.exception;

public class UsernameAndPasswordNotMatchException extends RuntimeException{
    public UsernameAndPasswordNotMatchException(String msg){
        super(msg);
    }
}
