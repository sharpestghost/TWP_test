package ru.tinkoff.edu.domain.repo;

import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.exception.InvalidInputDataException;

import java.util.List;

@Repository
public interface ChatRepo extends QueryRepo<Chat> {
    void register(Chat chat) throws InvalidInputDataException;
    void remove(long id);
    void remove(String name);
    Chat get(long chatId);
    List<Link> findAllLinks(long chatId);
}
