package ru.tinkoff.edu.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.exception.InvalidInputDataException;
import ru.tinkoff.edu.service.ChatService;

@RestController
@RequestMapping(name = "/tg-chat/{id}")
@AllArgsConstructor
public class ChatActionsController {
    private final ChatService chatService;
    private static final String TEXT_FIELD = "Lorem ipsum";
    public void createChat(@PathVariable long id) throws InvalidInputDataException {
            chatService.register(id, TEXT_FIELD);
    }

    public void deleteChat(@PathVariable long id) {
        chatService.unregister(id);
    }
}
