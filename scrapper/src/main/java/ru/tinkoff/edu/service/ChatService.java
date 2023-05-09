package ru.tinkoff.edu.service;

import ru.tinkoff.edu.exception.InvalidInputDataException;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.entity.Link;

import java.util.List;

public interface ChatService {
    void register(long tgChatId, String name) throws InvalidInputDataException;
    void unregister(long tgChatId);
    Chat getById(long tgChatId);
    List<Chat> findAll();
}
