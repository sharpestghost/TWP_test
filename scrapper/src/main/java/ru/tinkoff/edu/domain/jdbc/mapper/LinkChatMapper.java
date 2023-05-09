package ru.tinkoff.edu.domain.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.entity.LinkChat;

@Component
public class LinkChatMapper implements RowMapper<LinkChat> {
    @Override
    public LinkChat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LinkChat(rs.getLong("chat_id"), rs.getLong("link_id"));
    }
}
