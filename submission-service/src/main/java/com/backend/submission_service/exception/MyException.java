package com.backend.submission_service.exception;

public class MyException extends Exception{
    String message;

    public MyException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
