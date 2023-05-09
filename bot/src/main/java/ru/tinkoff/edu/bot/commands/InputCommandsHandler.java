package ru.tinkoff.edu.bot.commands;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.HashMap;
import java.util.Map;

public class InputCommandsHandler {
    private static final Map<String, CommandInfo> COMMANDS = BotCommandsList.getCommands();
    private static final Map<Long, CommandInfo> USER_LAST_COMMANDS = new HashMap<>();

    public SendMessage run(Update update) {
        Message message = update.message();
        try {
            CommandInfo baseCommand = COMMANDS.get(message.text());
            return baseCommand.handle(update);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void addLastAction(long id, CommandInfo command) {
        USER_LAST_COMMANDS.put(id, command);
    }

    public CommandInfo checkLastAction(long id) {
        CommandInfo lastCommand = USER_LAST_COMMANDS.remove(id);
        return lastCommand;
    }

}
