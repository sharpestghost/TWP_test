package ru.tinkoff.edu.client;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.GithubRepo;
import ru.tinkoff.edu.dto.response.QuestionResponse;
import ru.tinkoff.edu.dto.response.RepoResponse;

import java.time.Duration;
import java.time.OffsetDateTime;

public class GithubClient {

    private static final String GITHUB_URL = "https://api.github.com";
    private final WebClient webClient;

    public GithubClient() {
        webClient = returnBaseClient();
    }

    public GithubClient(String url) {
        webClient = returnClientByLink(url);
    }

    @Bean
    public WebClient returnBaseClient() {
        return WebClient.builder().baseUrl(GITHUB_URL).build();
    }

    @Bean
    public WebClient returnClientByLink(String url) {
        return WebClient.builder().baseUrl(url).build();
    }


    public RepoResponse getRepo(String user, String repo) {
        return webClient.get()
                .uri("/repos/{user}/{repo}", user, repo)
                .retrieve()
                .bodyToMono(RepoResponse.class)
                .timeout(Duration.ofSeconds(15))
                .block();
    }

}