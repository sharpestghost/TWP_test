package ru.tinkoff.edu.service.sender;

import ru.tinkoff.edu.entity.Link;

public interface SendUpdater {
    void sendUpdates(Link link, String info);
}
