package ru.tinkoff.edu.service.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.client.BotClient;
import ru.tinkoff.edu.dto.request.LinkUpdate;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.service.LinkChatService;
import ru.tinkoff.edu.service.LinkService;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HttpSendUpdater implements SendUpdater {
    private final BotClient client;
    private final LinkChatService linkChatService;

    @Override
    public void sendUpdates(Link link, String info) {
        List<Chat> chats = linkChatService.getChatsByLink(link.getId());
        LinkUpdate request = new LinkUpdate(link.getId(), URI.create(link.getURL()), info,
                chats.stream().mapToLong(Chat::getId).boxed().toList());
        client.postUpdate(request);
    }
}
