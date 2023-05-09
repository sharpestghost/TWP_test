package ru.tinkoff.edu.domain.jdbc.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.converter.EntityConverter;
import ru.tinkoff.edu.domain.jdbc.mapper.ChatMapper;
import ru.tinkoff.edu.domain.jdbc.mapper.LinkChatMapper;
import ru.tinkoff.edu.domain.jdbc.mapper.LinkMapper;
import ru.tinkoff.edu.domain.repo.ChatRepo;
import ru.tinkoff.edu.domain.repo.LinkChatRepo;
import ru.tinkoff.edu.domain.repo.LinkRepo;
import ru.tinkoff.edu.exception.DataNotFoundException;
import ru.tinkoff.edu.exception.InvalidInputDataException;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.entity.LinkChat;

import java.util.List;

@Repository
@AllArgsConstructor
public class LinkChatImpl implements LinkChatRepo {
    private final JdbcTemplate template;
    private final LinkRepo linkRepo;
    private final ChatRepo chatRepo;
    private final ChatMapper chatRowMapper;
    private final LinkMapper linkRowMapper;
    private static final String INSERT_CHATLINK = "INSERT INTO link_chat (chat_id, link_id) VALUES (?, ?)";
    private static final String REMOVE_CHATLINK = "DELETE FROM link_chat WHERE chat_id=? AND link_id=?";
    private static final String SELECT_ALL = "SELECT * FROM link_chat";
    private static final String SELECT_LINK = "SELECT COUNT(*) FROM link";
    private static final String SELECT_LINKS_BY_CHAT_ID = "SELECT * FROM link WHERE id IN (SELECT link_id FROM link_chat WHERE chat_id=?)";
    private static final String SELECT_CHATLINK = "SELECT FROM link_chat WHERE chat_id=? AND link_id = ?";
    private static final String INSERT_CHATLINK_ALREADYEXISTS = "This link is already tracking in this chat.";
    private static final String REMOVE_NOTFOUND = "Link not found.";
    private static final String REMOVE_OK = "Link successfully untracked";


    @Transactional
    @Override
    public List<Link> getLinksByChatId(long chatId) {
        Chat chat = chatRepo.get(chatId);
        if (chat == null) {
            throw new DataNotFoundException(REMOVE_NOTFOUND);
        }
        return template.query(SELECT_LINKS_BY_CHAT_ID, linkRowMapper, chatId);
    }

    @Transactional
    @Override
    public Link track(long chatId, String url) throws InvalidInputDataException {
        Link link = EntityConverter.createLink(url);
        long linkId = link.getId();
        int cnt = template.update(SELECT_LINK, linkId);
        if (cnt == 0) {
            linkRepo.add(link);
        }
        cnt = template.update(SELECT_CHATLINK, chatId, linkId);
        if (cnt > 0) {
            throw new DataNotFoundException(INSERT_CHATLINK_ALREADYEXISTS);
        } else {
            template.update(INSERT_CHATLINK, chatId, linkId);
        }
        return link;
    }



    @Override
    @Transactional
    public Link untrack(long chatId, String url) {
        Link link = EntityConverter.createLink(url);
        int result = template.update(REMOVE_CHATLINK, chatId, link.getId());
        if (result == 0) {
            throw new DataNotFoundException(INSERT_CHATLINK_ALREADYEXISTS);
        } else {
            System.out.println(REMOVE_OK);
        }
        return link;
    }

    @Override
    @Transactional
    public List<Chat> getChatsByLinkId(long linkId) {
        return template.query(SELECT_LINKS_BY_CHAT_ID, chatRowMapper, linkId);
    }

    @Override
    public List<LinkChat> findAll() {
        return template.query(SELECT_ALL, new LinkChatMapper());
    }
}
