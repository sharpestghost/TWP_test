package ru.tinkoff.edu.bot.commands;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import ru.tinkoff.edu.scrapperlink.client.ScrapperClient;
@AllArgsConstructor
public class HelpCommand implements CommandInfo {
    private final ScrapperClient client;
    private static final String HELP_MESSAGE = "To get a list of commands, enter help.";
    private static final String HELP_DESCRIPTION = "List of available commands.";

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return HELP_DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        Message msg = update.message();
        if (supports(update)) {
            StringBuilder helpStringBuilder = new StringBuilder();
            for (CommandInfo command :BotCommandsList.getCommands().values()) {
                helpStringBuilder.append(command.toString()).append("\n");
            }
        } else {
            return new SendMessage(msg.chat().id(), STANDARD_ERROR_MSG);
        }
        return new SendMessage(msg.chat().id(), supports(update)
                ? BotCommandsList.getCommands().toString() : STANDARD_ERROR_MSG);
    }
}
