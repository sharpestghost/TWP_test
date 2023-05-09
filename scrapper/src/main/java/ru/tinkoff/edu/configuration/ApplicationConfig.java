package ru.tinkoff.edu.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull String test,
                                @NotNull Scheduler scheduler,
                                @NotNull AccessType dbAccessType,
                                @NotNull String queueName,
                                @NotNull String exchangeName,
                                @NotNull boolean useQueue) {

public record Scheduler(Duration interval) {

}
enum AccessType {
    JDBC,
    JPA,
    JOOQ
}
}