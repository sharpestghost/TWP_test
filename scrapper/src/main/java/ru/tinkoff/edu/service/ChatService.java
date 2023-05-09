package ru.tinkoff.edu.service;

import java.util.List;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.exception.InvalidInputDataException;

public interface ChatService {

    void register(long tgChatId, String name) throws InvalidInputDataException;

    void unregister(long tgChatId);

    Chat getById(long tgChatId);

    List<Chat> findAll();
}
