package ru.tinkoff.edu.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.domain.repo.ChatRepo;
import ru.tinkoff.edu.domain.repo.LinkChatRepo;
import ru.tinkoff.edu.domain.repo.LinkRepo;
import ru.tinkoff.edu.entity.LinkChat;
import ru.tinkoff.edu.service.ChatService;
import ru.tinkoff.edu.service.LinkChatService;
import ru.tinkoff.edu.service.LinkService;
import ru.tinkoff.edu.service.jdbc.JdbcChatService;
import ru.tinkoff.edu.service.jdbc.JdbcLinkChatService;
import ru.tinkoff.edu.service.jdbc.JdbcLinkService;

@Configuration
@ConditionalOnProperty(prefix = "app", havingValue = "jdbc", name = "db-access-type")
public class JdbcAccessConfig {

    @Bean
    public LinkService linkService(LinkRepo linkRepo, ChatRepo chatRepo) {
        return new JdbcLinkService(linkRepo, chatRepo);
    }

    @Bean
    public ChatService chatService(ChatRepo chatRepo) {
        return new JdbcChatService(chatRepo);
    }

    @Bean
    public LinkChatService<LinkChat> linkChatService(LinkChatRepo linkChatRepo) {
        return new JdbcLinkChatService(linkChatRepo);
    }
}
