package ru.tinkoff.edu.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {
    private final ApplicationConfig config;
    public final String QUEUE_MESSAGES_DLQ = config.queueName() + ".dlx";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(config.exchangeName(), false, false);
    }

    @Bean("queueBean")
    public Queue queue() {
        return QueueBuilder.nonDurable(config.queueName())
                .withArgument("x-dead-letter-exchange", QUEUE_MESSAGES_DLQ)
                .build();
    }

    @Bean("bindingBean")
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(config.queueName());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
