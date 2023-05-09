package ru.tinkoff.edu;

import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.configuration.ApplicationConfig;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
        private static final Logger LOGGER = Logger.getGlobal();

        public static void main(String[] args) {
                var ctx = SpringApplication.run(BotApplication.class, args);
                ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
                LOGGER.info(config.toString());
        }
}
