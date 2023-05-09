package ru.tinkoff.edu.service;

import java.util.List;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.exception.InvalidInputDataException;


public interface LinkChatService<T> {
    void add(Long chatId, String url) throws InvalidInputDataException;

    Link untrack(Long chatId, String url);

    List<T> findAll();

    List<Link> getLinksByChatId(long chatId);

    List<Chat> getChatsByLink(long linkId);
}
