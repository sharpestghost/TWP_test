package ru.tinkoff.edu.bot.commands;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class InputCommandsHandler {
    private static final Map<String, CommandInfo> botCommandsList = BotCommandsList.getCommands();
    private static final Map<Long, CommandInfo> userLastCommands = new HashMap<>();
    public SendMessage run(Update update) {
        Message message = update.message();
        try {
            CommandInfo baseCommand = botCommandsList.get(message.text());
            return baseCommand.handle(update);
        } catch (NullPointerException e) {
            return null;
        }
    }
    public void addLastAction(long id, CommandInfo command) {
        userLastCommands.put(id, command);
    }

    public CommandInfo checkLastAction(long id) {
        CommandInfo lastCommand = userLastCommands.remove(id);
        return lastCommand;
    }
/*
    private SendMessage checkReplyMessage(Message message) {
        try {
            ReplyCommand replyCommand = REPLY_COMMANDS.get(message.replyToMessage().text());
            return replyCommand.executeReply(message);
        } catch (NullPointerException e) {
            return InvalidCommand.execute(message);
        }
    }

 */
}
