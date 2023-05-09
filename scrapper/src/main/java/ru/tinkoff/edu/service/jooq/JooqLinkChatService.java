package ru.tinkoff.edu.service.jooq;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.converter.EntityConverter;
import ru.tinkoff.edu.domain.jooq.Tables;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.entity.LinkChat;
import ru.tinkoff.edu.exception.DataNotFoundException;
import ru.tinkoff.edu.exception.InvalidInputDataException;
import ru.tinkoff.edu.service.LinkChatService;

@Service
@AllArgsConstructor
public class JooqLinkChatService implements LinkChatService<LinkChat> {
    private DSLContext context;
    private static final String INSERT_LINKCHAT_ALREADYEXISTS = "This link is already tracking.";
    private static final String REMOVE_LINKCHAT_NOTFOUND = "Tracked link not found.";

    @Transactional
    @Override
    public void add(Long chatId, String url) throws InvalidInputDataException {
        Link link = EntityConverter.createLink(url);
        int linkId = link.getId().intValue();
        int cnt = context.selectCount()
                .from(Tables.LINK_CHAT)
                .where(Tables.LINK_CHAT.CHAT_ID.eq(chatId.intValue()))
                .and(Tables.LINK_CHAT.LINK_ID.eq(linkId))
                .execute();
        if (cnt > 0) {
            throw new DataNotFoundException(INSERT_LINKCHAT_ALREADYEXISTS);
        } else {
            context.insertInto(Tables.LINK_CHAT, Tables.LINK_CHAT.fields()).values(chatId.intValue(), linkId)
                    .execute();
        }
    }

    @Transactional
    @Override
    public Link untrack(Long chatId, String url) {
        Link link = EntityConverter.createLink(url);
        int cnt = context.deleteFrom(Tables.LINK_CHAT)
                .where(Tables.LINK_CHAT.CHAT_ID.eq(chatId.intValue()))
                .and(Tables.LINK_CHAT.LINK_ID.eq(link.getId().intValue()))
                .execute();
        if (cnt == 0) {
            throw new DataNotFoundException(REMOVE_LINKCHAT_NOTFOUND);
        }
        return link;
    }

    @Override
    public List<LinkChat> findAll() {
        return context.select(Tables.LINK_CHAT.fields())
                .from(Tables.LINK_CHAT)
                .fetchInto(LinkChat.class);
    }

    @Override
    public List<Link> getLinksByChatId(long chatId) {
        //duplicate method from chatservice, fix it from one of next commits
        Set<Integer> linkIds = new HashSet<>(context.select(Tables.LINK_CHAT.LINK_ID)
                .from(Tables.LINK)
                .where(Tables.LINK_CHAT.CHAT_ID.eq((int) chatId))
                .fetchInto(Integer.class));
        return context.select(Tables.LINK.fields())
                .from(Tables.LINK)
                .where(Tables.LINK.ID.in(linkIds))
                .fetchInto(Link.class);
    }

    @Override
    public List<Chat> getChatsByLink(long linkId) {
        //duplicate method from linkservice, fix it from one of next commits
        Set<Integer> chatIds = new HashSet<>(context.select(Tables.LINK_CHAT.CHAT_ID)
                .from(Tables.LINK)
                .where(Tables.LINK_CHAT.LINK_ID.eq((int) linkId))
                .fetchInto(Integer.class));
        return context.select(Tables.CHAT.fields())
                .from(Tables.CHAT)
                .where(Tables.CHAT.ID.in(chatIds))
                .fetchInto(Chat.class);
    }
}
