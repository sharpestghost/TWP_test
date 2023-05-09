package ru.tinkoff.edu.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import ru.tinkoff.edu.bot.logic.ChatProcessing;
@AllArgsConstructor
public class StartCommand implements CommandInfo {

    private static final String START_MESSAGE = "Enter your username";
    private static final String START_DESCRIPTION = "User registration for link tracking";
    private static final String START_OK = "User succefully registred";

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return START_DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        return supports(update) ? new SendMessage(update.message().chat().id(), START_MESSAGE):
                handleReply(update);
    }

    private SendMessage handleReply(Update update) {
        long id = update.message().chat().id();
        try {
            ChatProcessing.add(id, update.message().text());
        } catch (RuntimeException ex) {
            new SendMessage(id, ex.getMessage());
        }
        return new SendMessage(id, START_OK);
    }
}
