package ru.tinkoff.edu.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.converter.EntityConverter;
import ru.tinkoff.edu.domain.jpa.JpaChatRepo;
import ru.tinkoff.edu.domain.jpa.JpaLCRepo;
import ru.tinkoff.edu.domain.jpa.JpaLinkRepo;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.entity.Follow;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.entity.LinkChat;
import ru.tinkoff.edu.exception.InvalidInputDataException;
import ru.tinkoff.edu.service.LinkChatService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JpaLCService implements LinkChatService<Follow> {
    private final JpaChatRepo chatRepo;
    private final JpaLinkRepo linkRepo;
    private final JpaLCRepo linkChatRepo;
    @Override
    public void add(Long chatId, String url) throws InvalidInputDataException {
        //rewrite this
    }

    @Override
    public Link untrack(Long chatId, String url) {
        Chat chat = chatRepo.findById(chatId).orElse(null);
        Link link = linkRepo.findByLink(url).orElse(null);
        if (chat == null || link == null) {
            throw new InvalidInputDataException();
        }
        Follow follow = new Follow();
        follow.setLink(link);
        follow.setChat(chat);
        return link;
    }

    @Transactional
    @Override
    public List<Follow> findAll() {
        return linkChatRepo.findAll();
    }

    @Transactional
    @Override
    public List<Link> getLinksByChatId(long chatId) {
        Optional<Chat> chat = chatRepo.findById(chatId);
        return chat.map(value -> linkChatRepo.getLinksByChatId(value.getId()))
                .orElse(null);
    }

    @Transactional
    @Override
    public List<Chat> getChatsByLink(long linkId) {
        Optional<Link> link = linkRepo.findById(linkId);
        return link.map(value -> linkChatRepo.getChatsByLinkId(value.getId()))
                .orElse(null);
    }
}
