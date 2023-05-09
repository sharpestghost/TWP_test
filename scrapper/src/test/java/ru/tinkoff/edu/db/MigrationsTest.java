package ru.tinkoff.edu.db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

public class MigrationsTest extends IntegrationEnviroment {

    @Test
    void checkIfExist() {
        String[] validTableNames = new String[]{"chat", "link", "link_chat"};
        String[] invalidTableNames = new String[]{"student", "users", "people", "chats", "links"};
        String selectQuery = "SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'";
        Set<String> tableNames = new HashSet<>();
        try (Connection conn = DB_CONTAINER.createConnection("")) {
            ResultSet result = conn.createStatement().executeQuery(selectQuery);
            while (result.next()) {
                tableNames.add(result.getString("table_name"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        //checking for tables in database
        for (String name : validTableNames) {
            assertTrue(tableNames.contains(name));
        }
        for (String name : invalidTableNames) {
            assertFalse(tableNames.contains(name));
        }
    }


}
