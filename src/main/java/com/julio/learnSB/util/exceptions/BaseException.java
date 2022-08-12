package com.julio.learnSB.util.exceptions;

public class BaseException extends RuntimeException{
    public BaseException(String msg) {
        super(msg);
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return null;
    }
}
