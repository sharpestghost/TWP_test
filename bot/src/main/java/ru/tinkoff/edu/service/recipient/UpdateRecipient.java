package ru.tinkoff.edu.service.recipient;

import ru.tinkoff.edu.scrapperlink.dto.request.LinkUpdate;

public interface UpdateRecipient {
    void recieveUpdate(LinkUpdate request);
}
