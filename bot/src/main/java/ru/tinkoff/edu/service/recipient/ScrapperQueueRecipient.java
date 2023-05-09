package ru.tinkoff.edu.service.recipient;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.tinkoff.edu.bot.Bot;
import ru.tinkoff.edu.scrapperlink.dto.request.LinkUpdate;

@AllArgsConstructor
@RabbitListener(queues = "${app.queue-name}")
public class ScrapperQueueRecipient implements UpdateRecipient {
    private final Bot bot;
    @RabbitHandler
    @Override
    public void recieveUpdate(LinkUpdate request) {
        bot.sendMessage(request);
    }
}
