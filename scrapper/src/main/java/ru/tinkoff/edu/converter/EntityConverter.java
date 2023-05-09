package ru.tinkoff.edu.converter;

import java.net.URI;
import java.util.logging.Logger;
import ru.tinkoff.edu.GithubRepo;
import ru.tinkoff.edu.LinkParser;
import ru.tinkoff.edu.ParsedObject;
import ru.tinkoff.edu.StackOverflowQuestion;
import ru.tinkoff.edu.client.GithubClient;
import ru.tinkoff.edu.client.StackOverflowClient;
import ru.tinkoff.edu.dto.response.QuestionResponse;
import ru.tinkoff.edu.dto.response.RepoResponse;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.exception.InvalidInputDataException;


public class EntityConverter {
    private static final GithubClient GITHUB_CLIENT = new GithubClient();
    private static final StackOverflowClient STACK_OVERFLOW_CLIENT = new StackOverflowClient();
    private static final Logger LOGGER = Logger.getGlobal();

    public static Link createLink(String url) throws InvalidInputDataException {
        ParsedObject object = LinkParser.parseLink(url);
        if (object == null) {
            throw new InvalidInputDataException();
        }
        Link link = new Link();
        link.setUrl(url);
        link.setLinkName("test");
        if (object instanceof GithubRepo repo) {
            RepoResponse response = getResponse(repo);
            link.setLastUpdateDate(response.updated_at());
        } else {
            if (object instanceof StackOverflowQuestion question) {
                QuestionResponse response = getQuestion(question);
                link.setLastUpdateDate(response.last_activity_date());
                link.setAnswerCount(response.answer_count());
            }
        }
        LOGGER.info("Converted link:" + link);
        return link;
    }

    public static Link createLink(URI url) throws InvalidInputDataException {
        return createLink(url.toString());
    }

    private void convert() {
    }

    public static RepoResponse getResponse(GithubRepo repo) {
        return GITHUB_CLIENT.getRepo(repo.user(), repo.repo());
    }

    public static QuestionResponse getQuestion(StackOverflowQuestion question) {
        return STACK_OVERFLOW_CLIENT.getQuestion(question.id());
    }
}
