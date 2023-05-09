package ru.tinkoff.edu.dto.response;

import java.time.OffsetDateTime;

public record StackOverflowResponse(long id, String title, OffsetDateTime updatedAt) {
}