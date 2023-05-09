package ru.tinkoff.edu.service.updaters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.ParsedObject;
import ru.tinkoff.edu.StackOverflowQuestion;
import ru.tinkoff.edu.converter.EntityConverter;
import ru.tinkoff.edu.dto.response.QuestionResponse;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.service.LinkService;
import ru.tinkoff.edu.service.LinkUpdater;
import ru.tinkoff.edu.service.sender.SendUpdater;

@AllArgsConstructor
@Service
public class StackOverflowLinksUpdater implements LinkUpdater {
    private final LinkService linkService;
    private final SendUpdater updater;
    private static final String UPDATE_OK = "Link was succefully updated.";


    @Override
    public void update(ParsedObject question, Link link) {

        QuestionResponse response = EntityConverter.getQuestion((StackOverflowQuestion) question);
        if (response.answer_count() > link.getAnswerCount()) {
            link.setAnswerCount(response.answer_count());
            updater.sendUpdates(link, UPDATE_OK);
        }
        if (response.last_activity_date().isAfter(link.getLastUpdateDate())) {
            link.setLastUpdateDate(response.last_activity_date());
            updater.sendUpdates(link, UPDATE_OK);
        }
        linkService.updateLinkData(link);


    }


}
