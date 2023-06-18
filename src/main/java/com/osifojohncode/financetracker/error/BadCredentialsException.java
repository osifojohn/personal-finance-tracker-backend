package com.osifojohncode.financetracker.error;

public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException(String msg) {
        super(msg);
    }

    public BadCredentialsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
