package ru.tinkoff.edu.bot.commands;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.net.URI;
import lombok.AllArgsConstructor;
import ru.tinkoff.edu.bot.logic.LinkProcessing;

@AllArgsConstructor
public class TrackCommand implements CommandInfo {

    private static final String TRACK_DESCRIPTION = "Add links to track";
    private static final String TRACK_MESSAGE = "Send the link as a reply to add this to tracking list";
    private static final String TRACK_OK = "Link was successfully add to tracking list";

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return TRACK_DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        return supports(update) ? new SendMessage(update.message().chat().id(), TRACK_MESSAGE)
                : handleReply(update);
    }

    private SendMessage handleReply(Update update) {
        Message msg = update.message();
        Long chatId = msg.chat().id();
        try {
            URI dsd = LinkProcessing.validate(msg.text());
            LinkProcessing.add(dsd);
        } catch (RuntimeException ex) {
            return new SendMessage(chatId, ex.getMessage());
        }
        return new SendMessage(chatId, TRACK_OK);
    }
}
