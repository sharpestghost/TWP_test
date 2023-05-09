package ru.tinkoff.edu.db;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.configuration.DBConfiguration;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.domain.jdbc.impl.ChatImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;



@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ContextConfiguration(classes = {DBConfiguration.class, TestConfiguration.class})
public class JdbcChatTest extends IntegrationEnviroment {
    private final ChatImpl chatImpl;
    private final JdbcTemplate jdbcTemplate;
    private static final String ADD_TEMPLATE = "INSERT INTO chat(id, chatname, description) VALUES (?, ?, ?)";
    private static final String SELECT_MAX_ID = "SELECT COALESCE(MAX(id), 0) FROM chat";
    private static final String SELECT_COUNT = "SELECT COUNT * FROM chat";
    private Chat chat;


    @Test
    @Transactional
    @Rollback
    void addUniqueChat_OK() {
        long id = selectMaxId();
        String name = "test";
        String descr = "test";
        try (Connection conn = DB_CONTAINER.createConnection("")) {
            PreparedStatement ps = conn.prepareStatement(ADD_TEMPLATE);
            ps.setLong(1, id);
            ps.setString(2, name);
            ps.setString(3, descr);
            ps.executeQuery();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Test
    @Transactional
    @Rollback
    void addDuplicateChat_Error() {
        long id = selectMaxId();
        String name = "test";
        String descr = "test";
        try (Connection conn = DB_CONTAINER.createConnection("")) {
            PreparedStatement ps = conn.prepareStatement(ADD_TEMPLATE);
            ps.setLong(1, id);
            ps.setString(2, name);
            ps.setString(3, descr);
            ps.executeQuery();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Transactional
    @Rollback
    long selectMaxId() {
        long id = 0;
        try (Connection conn = DB_CONTAINER.createConnection("")) {
            ResultSet result = conn.createStatement().executeQuery(SELECT_MAX_ID);
            while (result.next()) {
                id = result.getLong("id");
            }
        } catch (SQLException e) {

        }
        return id;
    }

}