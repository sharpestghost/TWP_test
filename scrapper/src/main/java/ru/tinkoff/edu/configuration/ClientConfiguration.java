package ru.tinkoff.edu.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.client.BotClient;
import ru.tinkoff.edu.client.GithubClient;
import ru.tinkoff.edu.client.StackOverflowClient;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {

    @Bean("schedulerInterval")
    public long schedulerInterval(ApplicationConfig config) {
        return config.scheduler().interval().toSeconds();
    }
    @Bean
    public GithubClient gitHubClient() {
        return new GithubClient();
    }

    @Bean
    public StackOverflowClient stackOverflowClient() {
        return new StackOverflowClient();
    }

    @Bean
    public BotClient botClient() {
        return new BotClient();
    }

}