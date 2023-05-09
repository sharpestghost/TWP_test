package ru.tinkoff.edu.service.updaters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.GithubRepo;
import ru.tinkoff.edu.ParsedObject;
import ru.tinkoff.edu.StackOverflowQuestion;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.service.LinkUpdater;


@AllArgsConstructor
@Service
public class CommonLinksUpdater implements LinkUpdater {
    private final GithubLinksUpdater githubLinksUpdater;
    private final StackOverflowLinksUpdater stackOverflowLinksUpdater;

    @Override
    public void update(ParsedObject object, Link link) {
        if (object instanceof GithubRepo repo) {
            githubLinksUpdater.update(repo, link);
        } else if (object instanceof StackOverflowQuestion question) {
           stackOverflowLinksUpdater.update(question, link);
        }
    }
}
