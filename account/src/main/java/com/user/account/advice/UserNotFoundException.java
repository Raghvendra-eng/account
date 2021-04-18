package com.user.account.advice;


public class UserNotFoundException extends Exception{
    public UserNotFoundException(String msg){
        super(msg);
    }
}

