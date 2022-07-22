package com.alda.fptlab.exception;

public class SubscriptionTypeNotFoundException extends Exception{

    public SubscriptionTypeNotFoundException() {
        super();
    }

    public SubscriptionTypeNotFoundException(String message) {
        super(message);
    }

    public SubscriptionTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SubscriptionTypeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
