package ru.tinkoff.edu.domain.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.entity.Chat;

@Component
public class ChatMapper implements RowMapper<Chat> {

    @Override
    public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Chat(rs.getLong("id"),
                rs.getString("chatname"),
                getDateColumn(rs.getDate("updated_at")), getDateColumn(rs.getDate("created_at")));
    }

    private OffsetDateTime getDateColumn(Date date) {
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }
}
