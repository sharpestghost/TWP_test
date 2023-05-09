package ru.tinkoff.edu.client;

import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.dto.response.RepoResponse;


public class GithubClient {

    private static final String GITHUB_URL = "https://api.github.com";
    public static final long SECONDS_FOR_UPDATE = 30;
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
                .timeout(Duration.ofSeconds(SECONDS_FOR_UPDATE))
                .block();
    }

}
