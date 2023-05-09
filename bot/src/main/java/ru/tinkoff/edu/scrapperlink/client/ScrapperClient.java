package ru.tinkoff.edu.scrapperlink.client;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.configuration.ScrapperConfig;
import ru.tinkoff.edu.scrapperlink.dto.request.AddLinkRequest;
import ru.tinkoff.edu.scrapperlink.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.scrapperlink.dto.response.LinkResponse;
import ru.tinkoff.edu.scrapperlink.dto.response.ListLinksResponse;

public class ScrapperClient {
    private final WebClient webClient;
    private static final String BASE_URL = "#{base.url.scrapper}";
    private static final String TG_CHAT_ID_LINK = "/tg-chat/{id}";
    private static final String TG_CHAT_ID_HEADER = "tgChatId";
    private static final String LINKS = "/links";

    public ScrapperClient(String url) {
        webClient = WebClient.builder().baseUrl(url).build();
    }

    public ScrapperClient() {
        webClient = WebClient.builder().baseUrl(BASE_URL).build();
    }
    public void addChat(long id) {
        webClient.post().uri(TG_CHAT_ID_LINK, id).retrieve().toBodilessEntity().block();
    }

    public void deleteChat(long id) {
        webClient.delete().uri(TG_CHAT_ID_LINK, id).retrieve().toBodilessEntity().block();
    }

    public ListLinksResponse getLinks(long tgChatId) {
        return webClient.get().uri(LINKS, tgChatId).header(TG_CHAT_ID_HEADER, String.valueOf(tgChatId)).
                retrieve().bodyToMono(ListLinksResponse.class).block();
    }

    public LinkResponse addLink(long tgChatId, AddLinkRequest request) {
        return webClient.post().uri(LINKS).header(TG_CHAT_ID_HEADER, String.valueOf(tgChatId)).
                bodyValue(request).retrieve().bodyToMono(LinkResponse.class).block();
    }

    public LinkResponse deleteLink(long tgChatId, RemoveLinkRequest request) {
        return webClient.method(HttpMethod.DELETE).uri("/links", tgChatId)
                .header("Tg-Chat-Id", String.valueOf(request)).bodyValue(request)
                .retrieve().bodyToMono(LinkResponse.class).block();
    }
}
