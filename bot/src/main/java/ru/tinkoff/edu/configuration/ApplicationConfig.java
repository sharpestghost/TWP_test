package ru.tinkoff.edu.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull String test,
                                @NotNull String accessToken,
                                @NotNull String queueName,
                                @NotNull String exchangeName,
                                @NotNull boolean useQueue) {

}
