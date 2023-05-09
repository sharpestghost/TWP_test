package ru.tinkoff.edu.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.scrapperlink.client.ScrapperClient;

@Configuration
@RequiredArgsConstructor
public class ClientConfig {
    private final ScrapperConfig scrapperConfig;

    @Bean
    public ScrapperClient scrapperClient() {
        return new ScrapperClient(scrapperConfig.getBaseUrl());
    }
}