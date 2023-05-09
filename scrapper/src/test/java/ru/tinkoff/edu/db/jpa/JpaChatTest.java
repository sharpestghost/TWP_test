package ru.tinkoff.edu.db.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.domain.jpa.JpaChatRepo;
import ru.tinkoff.edu.entity.Chat;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JpaChatTest extends JpaCreateChatTest {

    @Autowired
    private JpaChatRepo chatRepo;
    @Transactional
    @Test
    public void register() {
        long id = selectMaxId();
        assertFalse(this.chatRepo.findById(id).isPresent());
        Chat chat = insert(id);
        chatRepo.save(chat);
        assertTrue(chatRepo.findById(id).isPresent());
        chatRepo.deleteById(id);
        assertFalse(chatRepo.findById(id).isPresent());
    }

    @Transactional
    @Test
    public void checkSize() {
        long id = selectMaxId();
        long initCount = chatRepo.count();
        int newChats = 5;
        for (int i = 0; i < newChats; i++) {
            Chat chat = insert(id + i);
            chatRepo.save(chat);
        }
        System.out.println(chatRepo.count() + " " + initCount + newChats);
        assertEquals(chatRepo.count(), initCount + newChats);
        chatRepo.deleteAll();
        assertEquals(chatRepo.count(), 0);
    }




}
