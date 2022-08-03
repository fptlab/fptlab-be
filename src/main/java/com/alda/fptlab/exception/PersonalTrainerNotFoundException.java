package com.alda.fptlab.exception;

public class PersonalTrainerNotFoundException extends Exception{
    public PersonalTrainerNotFoundException() {
        super();
    }

    public PersonalTrainerNotFoundException(String message) {
        super(message);
    }

    public PersonalTrainerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonalTrainerNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PersonalTrainerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
