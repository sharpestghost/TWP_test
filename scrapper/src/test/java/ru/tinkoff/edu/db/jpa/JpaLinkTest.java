package ru.tinkoff.edu.db.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.ScrapperApplication;
import ru.tinkoff.edu.db.IntegrationEnviroment;
import ru.tinkoff.edu.domain.jpa.JpaChatRepo;
import ru.tinkoff.edu.domain.jpa.JpaLCRepo;
import ru.tinkoff.edu.domain.jpa.JpaLinkRepo;
import ru.tinkoff.edu.entity.Chat;
import ru.tinkoff.edu.service.jpa.JpaLinkService;

import java.net.URI;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ScrapperApplication.class)
public class JpaLinkTest extends JpaCreateChatTest {

    @Autowired
    private JpaLinkService linkService;
    @Autowired
    private JpaChatRepo chatRepo;
    @Autowired
    private JpaLinkRepo linkRepo;
    @Autowired
    private JpaLCRepo linkChatRepo;
    private JpaChatTest chatTest;
    private static String TEST_NAME = "testname";
    private static int INSERTED_CHATS = 5;
    private static String[] LINKS = new String[] {"https://github.com/sharpestghost/TWP/pull/3",
            "https://github.com/williamfiset/Algorithms",
            "https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value"};

    @Transactional
    @Test
    public void track() {
        Chat chat = insert();
        long chatId = chat.getId();
        int initCnt = linkChatRepo.getLinksByChatId(chatId).size();
        for (String link: LINKS) {
            linkService.add(chat.getId(), URI.create(link));
            assertTrue(linkRepo.findByLink(link).isPresent());
            assertNotEquals(initCnt, linkChatRepo.getLinksByChatId(chatId).size());
        }
        assertEquals(initCnt + LINKS.length, linkChatRepo.getLinksByChatId(chatId).size());
    }
    @Transactional
    @Test
    public void untrack() {
    }


}
