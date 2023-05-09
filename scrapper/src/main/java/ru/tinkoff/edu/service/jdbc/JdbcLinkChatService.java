package ru.tinkoff.edu.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.converter.EntityConverter;
import ru.tinkoff.edu.entity.Follow;
import ru.tinkoff.edu.exception.InvalidInputDataException;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.entity.LinkChat;
import ru.tinkoff.edu.domain.repo.LinkChatRepo;
import ru.tinkoff.edu.service.LinkChatService;

import java.util.List;
@Service
@AllArgsConstructor
public class JdbcLinkChatService implements LinkChatService<LinkChat> {
    private final LinkChatRepo linkChatRepo;


    @Override
    public void add(Long chatId, String url) throws InvalidInputDataException {
       //rewrite this
    }

    @Override
    public Link untrack(Long chatId, String url) {
        Link link = EntityConverter.createLink(url);
        linkChatRepo.untrack(chatId, url);
        return link;
    }

    @Override
    public List<LinkChat> findAll() {
        return linkChatRepo.findAll();
    }

    @Override
    public List<Link> getLinksByChatId(long chatId) {
        return linkChatRepo.getLinksByChatId(chatId);
    }

    @Override
    public List<Chat> getChatsByLink(long linkId) {
        return linkChatRepo.getChatsByLinkId(linkId);
    }

}
