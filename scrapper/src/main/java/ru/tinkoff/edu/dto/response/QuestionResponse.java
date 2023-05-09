package ru.tinkoff.edu.dto.response;

import java.time.OffsetDateTime;

public record QuestionResponse(Long question_id, OffsetDateTime last_activity_date, Integer answer_count){
}
