package ru.tinkoff.edu;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.service.LinkService;
import ru.tinkoff.edu.service.updaters.CommonLinksUpdater;
import ru.tinkoff.edu.service.updaters.GithubLinksUpdater;
import ru.tinkoff.edu.service.updaters.StackOverflowLinksUpdater;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LinkUpdaterScheduler {

    private final LinkService linkService;
    private final CommonLinksUpdater updater;
    @Scheduled(fixedDelayString = "#{@schedulerInterval}")
    public void update() {
        List<Link> links = linkService.getLinksForUpdate();
        System.out.println("Update links...");
        for (Link link: links) {
            ParsedObject object = LinkParser.parseLink(link.getURL());
            if (object instanceof GithubRepo || object instanceof StackOverflowQuestion) {
                updater.update(object, link);
            }
        }
        System.out.println(links.isEmpty() ? "All links are up to date" : "Links updated:" + links.size());

    }
}