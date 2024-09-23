package com.hrm.human.resource.management.system.exception;

public class EmailOrPasswordIncorrectException extends RuntimeException{

    public EmailOrPasswordIncorrectException(String message) {super(message);}

    public EmailOrPasswordIncorrectException(String message, Throwable cause) {super(message,cause);}
}
