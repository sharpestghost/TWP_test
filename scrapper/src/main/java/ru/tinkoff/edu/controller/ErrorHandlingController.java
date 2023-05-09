package ru.tinkoff.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.exception.ChatNotFoundException;
import ru.tinkoff.edu.exception.DataNotFoundException;
import ru.tinkoff.edu.exception.InvalidRequestException;
import ru.tinkoff.edu.dto.response.ApiErrorResponse;

import java.util.Arrays;

@RestControllerAdvice
public class ErrorHandlingController {

    private static final int BAD_REQUEST_CODE = 400;
    private static final int NOT_FOUND = 404;
    private static final String BAD_REQUEST_DESCRIPTION = "Некорректные параметры запроса: ";
    private static final String LINK_NOT_FOUND_DESCRIPTION = "Ссылка не найдена: ";

    //Error: 400
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse invalidRequestParameters(InvalidRequestException e) {
        return new ApiErrorResponse(BAD_REQUEST_DESCRIPTION, String.valueOf(BAD_REQUEST_CODE),
                e.getClass().getName(), e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
    }

    //Error: 404
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse linkNotFound(DataNotFoundException e) {
        return new ApiErrorResponse(LINK_NOT_FOUND_DESCRIPTION, String.valueOf(NOT_FOUND),
                e.getClass().getName(), e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
    }

    @ExceptionHandler(ChatNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse chatNotFound(ChatNotFoundException e) {
        return new ApiErrorResponse(LINK_NOT_FOUND_DESCRIPTION, String.valueOf(NOT_FOUND),
                e.getClass().getName(), e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
    }


}
