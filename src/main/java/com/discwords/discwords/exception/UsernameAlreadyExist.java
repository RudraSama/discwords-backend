package com.discwords.discwords.exception;

public class UsernameAlreadyExist extends RuntimeException {
    public UsernameAlreadyExist(){

    }
    public UsernameAlreadyExist(String message){
        super(message);
    }
}
