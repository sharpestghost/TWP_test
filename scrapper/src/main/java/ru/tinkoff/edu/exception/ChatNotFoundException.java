package ru.tinkoff.edu.exception;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(String msg) {
        super(msg);
    }
}
