package ru.tinkoff.edu.service.sender;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.dto.request.LinkUpdate;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.service.LinkChatService;


@Service
@RequiredArgsConstructor
public class ScrapperQueueProducer implements SendUpdater {
    private final RabbitTemplate template;
    private final LinkChatService linkChatService;
    private final Queue queue;

    @Override
    public void sendUpdates(Link link, String info) {
        List<Chat> chats = linkChatService.getChatsByLink(link.getId());
        LinkUpdate request = new LinkUpdate(link.getId(), URI.create(link.getUrl()), info,
                chats.stream().mapToLong(Chat::getId).boxed().toList());
        template.convertAndSend(queue.getName(), request);
    }
}
