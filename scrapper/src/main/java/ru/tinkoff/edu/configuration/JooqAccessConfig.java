package ru.tinkoff.edu.configuration;

import org.jooq.DSLContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.service.ChatService;
import ru.tinkoff.edu.service.LinkService;
import ru.tinkoff.edu.service.jooq.JooqChatService;
import ru.tinkoff.edu.service.jooq.JooqLinkChatService;
import ru.tinkoff.edu.service.jooq.JooqLinkService;

@Configuration
@ConditionalOnProperty(prefix = "app", havingValue = "jooq", name = "db-access-type")
public class JooqAccessConfig {
    @Bean
    public LinkService linkService(DSLContext context) {
        return new JooqLinkService(context);
    }

    @Bean
    public ChatService chatService(DSLContext context) {
        return new JooqChatService(context);
    }

    @Bean
    public JooqLinkChatService linkChatService(DSLContext context) {
        return new JooqLinkChatService(context);
    }
}
