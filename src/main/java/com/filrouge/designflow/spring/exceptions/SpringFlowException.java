package com.filrouge.designflow.spring.exceptions;

public class SpringFlowException extends RuntimeException {

//  Custom exception with information
    public SpringFlowException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringFlowException(String exMessage) {
        super(exMessage);
    }
}
