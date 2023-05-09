package ru.tinkoff.edu.service.jdbc;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.domain.repo.ChatRepo;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.exception.InvalidInputDataException;
import ru.tinkoff.edu.service.ChatService;

@Service
@RequiredArgsConstructor
public class JdbcChatService implements ChatService {
    private final ChatRepo chatRepo;

    @Override
    public void register(long chatId, String name) throws InvalidInputDataException {
        Chat chat = new Chat();
        chat.setId(chatId);
        chat.setChatName(name);
        chatRepo.register(chat);
    }

    @Override
    public void unregister(long chatId) {
        chatRepo.remove(chatId);
    }

    @Override
    public Chat getById(long chatId) {
        return chatRepo.get(chatId);
    }

    @Override
    public List<Chat> findAll() {
        return chatRepo.findAll();
    }

}
