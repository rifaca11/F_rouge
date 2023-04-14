package com.filrouge.designflow.spring.exceptions;

public class SubflowNotFoundException extends RuntimeException {
    public SubflowNotFoundException(String message) {
        super(message);
    }
}
