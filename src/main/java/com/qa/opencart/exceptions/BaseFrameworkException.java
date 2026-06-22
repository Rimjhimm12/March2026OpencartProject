package com.qa.opencart.exceptions;

public abstract class BaseFrameworkException extends RuntimeException {

    public BaseFrameworkException(String message) {
        super(message);
    }
}