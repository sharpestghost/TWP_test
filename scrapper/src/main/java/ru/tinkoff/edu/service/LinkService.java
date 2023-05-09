package ru.tinkoff.edu.service;

import java.net.URI;
import java.util.List;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.exception.InvalidInputDataException;

public interface LinkService {
    Link add(Long tgChatId, URI url) throws InvalidInputDataException;

    Link remove(Long tgChatId, URI url) throws InvalidInputDataException;

    List<Link> listAll(Long tgChatId);

    List<Link> listAll();

    void updateLinkData(Link link);

    List<Link> getLinksForUpdate();
}

