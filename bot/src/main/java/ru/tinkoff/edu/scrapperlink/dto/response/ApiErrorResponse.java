package ru.tinkoff.edu.scrapperlink.dto.response;

public record ApiErrorResponse(String description, String code, String exceptionName, String exceptionMessage,
                               String[] stackTrace) {
}
