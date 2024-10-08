package com.discwords.discwords.exceptions;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(){
        super("User with this email already exists.");
    }
}
