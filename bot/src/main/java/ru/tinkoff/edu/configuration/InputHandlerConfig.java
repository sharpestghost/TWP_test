package ru.tinkoff.edu.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.bot.commands.InputCommandsHandler;

@Configuration
@RequiredArgsConstructor
public class InputHandlerConfig {

    @Bean("inputCommandsHandler")
    public InputCommandsHandler getHandler() {
        return new InputCommandsHandler();
    }
}
