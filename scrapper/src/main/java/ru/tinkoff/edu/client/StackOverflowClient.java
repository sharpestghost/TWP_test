package ru.tinkoff.edu.client;

import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.dto.response.QuestionResponse;
import ru.tinkoff.edu.dto.response.QuestionsResponse;


public class StackOverflowClient {
    private static final String STACKOVERFLOW_URL = "https://api.stackexchange.com/2.3/questions/";
    private final WebClient webClient;
    public static final long SECONDS_FOR_UPDATE = 30;

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
                .timeout(Duration.ofSeconds(SECONDS_FOR_UPDATE))
                .block();
        return response.items().get(0);
    }
}
