package com.discwords.discwords.exception;

public class UserAlreadyExist extends RuntimeException{
    public UserAlreadyExist(){}
    public UserAlreadyExist(String message){
        super(message);
    }
}
