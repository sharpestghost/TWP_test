package ru.tinkoff.edu.exception;

public class InvalidRequestException extends RuntimeException {
    InvalidRequestException(String msg) {
        super("Bad Request exception");
    }
}
