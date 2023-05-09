package ru.tinkoff.edu.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.client.BotClient;
import ru.tinkoff.edu.service.LinkChatService;
import ru.tinkoff.edu.service.sender.HttpSendUpdater;
import ru.tinkoff.edu.service.sender.SendUpdater;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "false")
public class HttpSenderConfig {

    @Bean
    public SendUpdater sendUpdater(BotClient botClient, LinkChatService linkChatService) {
        return new HttpSendUpdater(botClient, linkChatService);
    }

}
