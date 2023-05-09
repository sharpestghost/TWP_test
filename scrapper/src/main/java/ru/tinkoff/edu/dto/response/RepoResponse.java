package ru.tinkoff.edu.dto.response;

import java.time.OffsetDateTime;

@SuppressWarnings("checkstyle:RecordComponentName")
public record RepoResponse(Long id, String name, OffsetDateTime updated_at) {
}
