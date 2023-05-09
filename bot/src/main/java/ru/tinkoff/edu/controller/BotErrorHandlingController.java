package ru.tinkoff.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.tinkoff.edu.scrapperlink.dto.response.ApiErrorResponse;
import ru.tinkoff.edu.exception.InvalidRequestException;

import java.util.Arrays;

public class BotErrorHandlingController {

    private static final int BAD_REQUEST_CODE = 400;
    private static final String BAD_REQUEST_DESCRIPTION = "Некорректные параметры запроса: ";

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse invalidRequestParameters(InvalidRequestException e) {
        return new ApiErrorResponse(BAD_REQUEST_DESCRIPTION, String.valueOf(BAD_REQUEST_CODE),
                e.getClass().getName(), e.getMessage(),
                Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
    }

}
