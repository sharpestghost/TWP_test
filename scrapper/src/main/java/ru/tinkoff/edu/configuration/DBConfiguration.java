package ru.tinkoff.edu.configuration;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(PostgresqlConfig.class)
public class DBConfiguration {
    private final PostgresqlConfig postgresqlConfig;
    @Bean("dataSource")
    public DataSource getDataSource() {
        var dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(postgresqlConfig.url());
        dataSourceBuilder.username(postgresqlConfig.username());
        dataSourceBuilder.password(postgresqlConfig.password());
        return dataSourceBuilder.build();


    }

    @Bean("jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}
