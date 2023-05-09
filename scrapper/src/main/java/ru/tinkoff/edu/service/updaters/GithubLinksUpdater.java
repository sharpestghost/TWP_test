package ru.tinkoff.edu.service.updaters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.GithubRepo;
import ru.tinkoff.edu.ParsedObject;
import ru.tinkoff.edu.converter.EntityConverter;
import ru.tinkoff.edu.dto.response.RepoResponse;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.service.LinkService;
import ru.tinkoff.edu.service.LinkUpdater;
import ru.tinkoff.edu.service.sender.SendUpdater;

import java.time.OffsetDateTime;


@AllArgsConstructor
@Service
public class GithubLinksUpdater implements LinkUpdater {
    private final LinkService linkService;
    private final SendUpdater updater;
    private final String UPDATE_OK = "Link was succefully updated.";

    @Override
    public void update(ParsedObject repo, Link link) {
        RepoResponse response = EntityConverter.getResponse((GithubRepo) repo);
        link.setLastUpdateDate(OffsetDateTime.now());
        if (response.updated_at().isBefore(link.getLastUpdateDate())) {
            link.setLastUpdateDate(response.updated_at());
            updater.sendUpdates(link, UPDATE_OK);
        }
        linkService.updateLinkData(link);
    }


}
