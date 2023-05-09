package ru.tinkoff.edu.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.service.LinkChatService;
import ru.tinkoff.edu.service.sender.ScrapperQueueProducer;
import ru.tinkoff.edu.service.sender.SendUpdater;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
public class QueueProducerConfig {
    @Bean
    public SendUpdater linkUpdateSender(RabbitTemplate template, LinkChatService linkService, Queue queue) {
        return new ScrapperQueueProducer( template, linkService, queue);
    }
}
