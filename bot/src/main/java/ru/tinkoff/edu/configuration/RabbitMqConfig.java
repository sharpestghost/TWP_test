package ru.tinkoff.edu.configuration;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.scrapperlink.dto.request.LinkUpdate;


@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {
    private final ApplicationConfig config;
    public static final String DLX = ".dlx";

    @Bean
    public ClassMapper classMapper() {
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("ru.tinkoff.edu.dto.request.LinkUpdate", LinkUpdate.class);
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("ru.tinkoff.edu.dto.*");
        classMapper.setIdClassMapping(mappings);
        return classMapper;
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(config.exchangeName(), false, false);
    }

    @Bean
    public FanoutExchange deadDirectExchange() {
        return new FanoutExchange(config.exchangeName() + DLX, false, false);
    }

    @Bean("queueBean")
    public Queue queue() {
        return QueueBuilder.durable(config.queueName())
                .withArgument("x-dead-letter-exchange", config.queueName() + DLX)
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(config.queueName() + DLX).build();
    }

    @Bean("bindingBean")
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(config.queueName());
    }

    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadDirectExchange());
    }

    @Bean
    public MessageConverter jsonMessageConverter(ClassMapper classMapper) {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        messageConverter.setClassMapper(classMapper);
        return messageConverter;
    }

}
