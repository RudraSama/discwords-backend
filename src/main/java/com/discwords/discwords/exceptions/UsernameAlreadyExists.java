package com.discwords.discwords.exceptions;

public class UsernameAlreadyExists extends RuntimeException{
    public UsernameAlreadyExists(){
        super("Username Already Exists");
    }
}
