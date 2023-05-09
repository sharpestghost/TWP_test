package ru.tinkoff.edu;

import java.util.List;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.service.LinkService;
import ru.tinkoff.edu.service.updaters.CommonLinksUpdater;

@Component
@RequiredArgsConstructor
public class LinkUpdaterScheduler {

    private final LinkService linkService;
    private final CommonLinksUpdater updater;
    private static final Logger LOGGER = Logger.getGlobal();

    @Scheduled(fixedDelayString = "#{@schedulerInterval}")
    public void update() {
        List<Link> links = linkService.getLinksForUpdate();
        LOGGER.info("Update links...");
        for (Link link: links) {
            ParsedObject object = LinkParser.parseLink(link.getUrl());
            if (object instanceof GithubRepo || object instanceof StackOverflowQuestion) {
                updater.update(object, link);
            }
        }
        LOGGER.info(links.isEmpty() ? "All links are up to date" : "Links updated:" + links.size());

    }
}
