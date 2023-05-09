package ru.tinkoff.edu.db.jpa;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.db.IntegrationEnviroment;
import ru.tinkoff.edu.domain.jpa.JpaChatRepo;
import ru.tinkoff.edu.entity.Chat;

import java.util.Comparator;

@SpringBootTest
@RequiredArgsConstructor
public class JpaCreateChatTest extends IntegrationEnviroment {

    @Autowired
    private JpaChatRepo chatRepo;
    private static String TEST_NAME = "testname";

    public long selectMaxId() {
        return chatRepo.findAll().stream().map((Chat c) -> c.getId()).max(Comparator.naturalOrder()).orElse((long)0) + 1;
    }

    @Transactional
    public Chat insert(long id) {
        Chat chat = new Chat();
        chat.setId(id);
        chat.setChatName(TEST_NAME);
        return chat;
    }

    @Transactional
    public Chat insert() {
        Chat chat = new Chat();
        chat.setId(selectMaxId());
        chat.setChatName(TEST_NAME);
        chatRepo.save(chat);
        return chat;
    }
}
