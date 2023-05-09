package ru.tinkoff.edu.db;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class IntegrationEnviroment {
    private static final Path PATH = new File(".").toPath().toAbsolutePath().getParent();
    static final Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    static final PostgreSQLContainer<?> DB_CONTAINER;

    static {
        DB_CONTAINER = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("postgres")
                .withUsername("admin")
                .withPassword("secretpword");
        DB_CONTAINER.start();
        try (Connection conn = DB_CONTAINER.createConnection("")){
            PostgresDatabase database = new PostgresDatabase();
            database.setConnection(new JdbcConnection(conn));

            ResourceAccessor changelogDir = new DirectoryResourceAccessor(PATH.getParent().resolve("migrations"));
            Liquibase liquibase = new Liquibase("master.xml", changelogDir, database);
            liquibase.update(new Contexts(), new LabelExpression());
            liquibase.close();

        } catch (SQLException | FileNotFoundException | LiquibaseException e) {
            System.out.println("Something goes wrong...");
        }
    }
}
