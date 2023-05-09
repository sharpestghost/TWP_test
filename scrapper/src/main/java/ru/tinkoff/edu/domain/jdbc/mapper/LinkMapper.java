package ru.tinkoff.edu.domain.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.entity.Link;

@Component
public class LinkMapper implements RowMapper<Link> {
    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Link(rs.getLong("id"),
                rs.getString("url"), rs.getString("linkname"),
                rs.getObject("updated_at", OffsetDateTime.class), rs.getInt("answer_count"));
    }

    private OffsetDateTime getDateColumn(Date date) {
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }
}
