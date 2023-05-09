package ru.tinkoff.edu.service.jpa;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.domain.jpa.JpaChatRepo;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.exception.InvalidInputDataException;
import ru.tinkoff.edu.service.ChatService;

import java.util.List;

@RequiredArgsConstructor
public class JpaChatService implements ChatService {
    private final JpaChatRepo chatRepo;

    @Transactional
    @Override
    public void register(long chatId, String name) throws InvalidInputDataException {
        chatRepo.insertChat(chatId, name);
    }

    @Override
    public void unregister(long id) {
        Chat chat = getById(id);
        if (chat == null) {
            throw new InvalidInputDataException();
        }
        chatRepo.deleteById(chat.getId());
    }

    @Override
    public Chat getById(long id) {
        return chatRepo.findById(id).orElse(null);
    }

    @Override
    public List<Chat> findAll() {
        return chatRepo.findAll();
    }

}
