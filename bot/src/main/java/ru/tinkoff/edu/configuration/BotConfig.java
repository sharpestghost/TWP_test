package ru.tinkoff.edu.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.bot.Bot;
import ru.tinkoff.edu.bot.commands.*;
import ru.tinkoff.edu.scrapperlink.client.ScrapperClient;

import java.util.ArrayList;
import java.util.List;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record BotConfig(@NotNull String accessToken, @NotNull List<CommandInfo> commandsList) {

    @Bean
    public Bot bot(ApplicationConfig config, InputCommandsHandler handler, ScrapperClient client) {
        List<CommandInfo> supported = new ArrayList<>();
        supported.add(new ListCommand());
        supported.add(new HelpCommand(client));
        supported.add(new StartCommand());
        supported.add(new TrackCommand());
        supported.add(new UntrackCommand());
        return new Bot(config.accessToken(), supported, handler);
    }
}
