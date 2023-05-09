package ru.tinkoff.edu.client;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.dto.response.QuestionResponse;
import ru.tinkoff.edu.dto.response.QuestionsResponse;
import ru.tinkoff.edu.exception.InvalidInputDataException;

import java.time.Duration;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

public class StackOverflowClient {
    private static final String STACKOVERFLOW_URL = "https://api.stackexchange.com/2.3/questions/";
    private final WebClient webClient;

    public StackOverflowClient() {
        webClient = returnBaseClient();
    }

    public StackOverflowClient(String url) {
        webClient = returnClientByLink(url);
    }

    @Bean
    public WebClient returnBaseClient() {
        return WebClient.builder().baseUrl(STACKOVERFLOW_URL).build();
    }

    @Bean
    public WebClient returnClientByLink(String url) {
        return WebClient.builder().baseUrl(url).build();
    }

    public QuestionResponse getQuestion(Long id) {
        QuestionsResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(id.toString())
                        .queryParam("site", "stackoverflow")
                        .build())
                .retrieve()
                .bodyToMono(QuestionsResponse.class)
                .timeout(Duration.ofSeconds(10))
                .block();
        return response.items().get(0);
    }
}
