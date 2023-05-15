package com.flightreservation.exception;

public class CabinAlreadyExistException extends RuntimeException{
    public CabinAlreadyExistException(String msg){
        super(msg);
    }

}
