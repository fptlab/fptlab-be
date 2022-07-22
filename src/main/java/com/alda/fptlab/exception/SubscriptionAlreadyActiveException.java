package com.alda.fptlab.exception;

public class SubscriptionAlreadyActiveException extends Exception{
    public SubscriptionAlreadyActiveException() {
        super();
    }

    public SubscriptionAlreadyActiveException(String message) {
        super(message);
    }

    public SubscriptionAlreadyActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionAlreadyActiveException(Throwable cause) {
        super(cause);
    }

    protected SubscriptionAlreadyActiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
