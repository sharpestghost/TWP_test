package ru.tinkoff.edu.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.scrapperlink.dto.request.LinkUpdate;
import ru.tinkoff.edu.service.recipient.UpdateRecipient;

@RestController
@RequestMapping("/updates")
public class UpdatesController {
    private UpdateRecipient updateRecipient;
    public void update(@RequestBody LinkUpdate linkUpdate) {
        updateRecipient.recieveUpdate(linkUpdate);
    }
}
