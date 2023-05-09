package ru.tinkoff.edu.dto.response;

import java.time.OffsetDateTime;

@SuppressWarnings("checkstyle:RecordComponentName")
public record QuestionResponse(Long question_id, OffsetDateTime last_activity_date, Integer answer_count){
}
