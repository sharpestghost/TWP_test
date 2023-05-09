package ru.tinkoff.edu.exception;

public class InvalidLinkException extends RuntimeException {
    public InvalidLinkException(String msg) {
        super(msg);
    }
}
