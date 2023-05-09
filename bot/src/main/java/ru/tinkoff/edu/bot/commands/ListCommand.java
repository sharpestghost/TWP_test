package ru.tinkoff.edu.bot.commands;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.net.URI;
import java.util.Set;
import lombok.AllArgsConstructor;
import ru.tinkoff.edu.bot.logic.LinkProcessing;

@AllArgsConstructor
public class ListCommand implements CommandInfo {

    private static final String LIST_MESSAGE = "Current list of tracking links:";
    private static final String LIST_DESCRIPTION = "Provide a list of tracking lists.";
    private static final String SPECIAL_MESSAGE = "Tracking list is empty.";

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return LIST_DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        Message msg = update.message();
        Long chatId = msg.chat().id();
        if (supports(update)) {
            Set<URI> response = LinkProcessing.getUris();
            StringBuilder linksStringBuilder = new StringBuilder(!response.isEmpty()
                    ? LIST_MESSAGE : SPECIAL_MESSAGE);
                for (URI link : response) {
                    linksStringBuilder.append("\n").append(link.toString());
                }
                return new SendMessage(chatId, linksStringBuilder.toString());
        }
        return new SendMessage(chatId, STANDARD_ERROR_MSG);
    }
}
