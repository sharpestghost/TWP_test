package ru.tinkoff.edu.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.domain.jpa.JpaChatRepo;
import ru.tinkoff.edu.domain.jpa.JpaLCRepo;
import ru.tinkoff.edu.domain.jpa.JpaLinkRepo;
import ru.tinkoff.edu.entity.Follow;
import ru.tinkoff.edu.service.ChatService;
import ru.tinkoff.edu.service.LinkChatService;
import ru.tinkoff.edu.service.LinkService;
import ru.tinkoff.edu.service.jpa.JpaChatService;
import ru.tinkoff.edu.service.jpa.JpaLCService;
import ru.tinkoff.edu.service.jpa.JpaLinkService;

@Configuration
@ConditionalOnProperty(prefix = "app", havingValue = "jpa", name = "db-access-type")
public class JpaAccessConfig {

    @Bean
    public LinkService linkService(JpaChatRepo chatRepo, JpaLinkRepo linkRepo, JpaLCRepo linkChatRepo) {
        return new JpaLinkService(chatRepo, linkRepo, linkChatRepo);
    }

    @Bean
    public ChatService chatService(JpaChatRepo chatRepo) {
        return new JpaChatService(chatRepo);
    }

    @Bean
    public LinkChatService<Follow> linkChatService(JpaChatRepo chatRepo, JpaLinkRepo linkRepo, JpaLCRepo linkChatRepo) {
        return new JpaLCService(chatRepo, linkRepo, linkChatRepo);
    }


}
