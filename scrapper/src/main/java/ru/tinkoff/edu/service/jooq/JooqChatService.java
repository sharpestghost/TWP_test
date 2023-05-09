package ru.tinkoff.edu.service.jooq;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.domain.jooq.Tables;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.exception.ChatNotFoundException;
import ru.tinkoff.edu.exception.InvalidInputDataException;
import ru.tinkoff.edu.service.ChatService;

@AllArgsConstructor
@Service
public class JooqChatService implements ChatService {
    private DSLContext context;

    @Transactional
    @Override
    public void register(long chatId, String name) throws InvalidInputDataException {
        Chat chat = getById(chatId);
        if (chat == null) {
            LocalDateTime now = Instant.ofEpochMilli(new Date().getTime())
                            .atOffset(ZoneOffset.UTC).toLocalDateTime();
            context.insertInto(Tables.CHAT, Tables.CHAT.ID, Tables.CHAT.CHATNAME,
                            Tables.CHAT.CREATED_AT, Tables.CHAT.UPDATED_AT)
                    .values((int) chatId, name, now, now);
        }
        //add exception for duplicate later
    }

    @Transactional
    @Override
    public void unregister(long chatId) {
        int cnt = context.deleteFrom(Tables.CHAT)
                .where(Tables.CHAT.ID.eq((int) chatId))
                .execute();
        if (cnt == 0) {
            throw new ChatNotFoundException("Chat " + chatId + "not found.");
        }
        //add exception for duplicate later
    }

    @Override
    public Chat getById(long chatId) {
        List<Chat> chats = context.select(Tables.CHAT.fields())
                .from(Tables.CHAT)
                .where(Tables.CHAT.ID.eq((int) chatId))
                .fetchInto(Chat.class);
        return (chats.isEmpty()) ? null : chats.get(0);
    }

    @Override
    public List<Chat> findAll() {
        return context.select(Tables.CHAT.fields())
                .from(Tables.CHAT)
                .fetchInto(Chat.class);
    }

}
