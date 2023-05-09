package ru.tinkoff.edu.dto.response;

import java.time.OffsetDateTime;

public record GitHubResponse(long id, String title, OffsetDateTime updatedAt) {
}
