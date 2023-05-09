package ru.tinkoff.edu.client;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.dto.request.LinkUpdate;



@RequiredArgsConstructor
public class BotClient {

    private static final String BASE_URL = "http://localhost:8080";
    public static final long MS_FOR_UPDATE = 30;
    private final WebClient webClient;

    public BotClient() {
        webClient = WebClient.builder().baseUrl(BASE_URL).build();
    }

    public BotClient(String url) {
        webClient = WebClient.builder().baseUrl(url).build();
    }

    public void postUpdate(LinkUpdate request) {
        webClient.post().uri("updates").body(Mono.just(request), LinkUpdate.class)
                .retrieve().bodyToMono(Void.class).timeout(Duration.ofMillis(MS_FOR_UPDATE))
                .block();
    }

}
