package ru.tinkoff.edu.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import ru.tinkoff.edu.bot.commands.CommandInfo;
import ru.tinkoff.edu.bot.commands.InputCommandsHandler;
import ru.tinkoff.edu.scrapperlink.dto.request.LinkUpdate;

public class Bot {
    public static TelegramBot telegramBot;
    private final List<CommandInfo> supportedCommands;
    private final InputCommandsHandler inputHandler;
    private static final String INVALID_COMMAND = "Invalid command option.";

    public Bot(@Value("${app.accessToken}") String accessToken, List<CommandInfo> supportedCommands, InputCommandsHandler handler) {
        this.supportedCommands = supportedCommands;
        this.inputHandler = handler;
        telegramBot = new TelegramBot(accessToken);
    }

    public void start() {
        BotCommand[] commands = new BotCommand[supportedCommands.size()];
        for (int i = 0; i < commands.length; i++) {
            commands[i] = supportedCommands.get(i).toApiCommand();
        }
        telegramBot.execute(new SetMyCommands(commands));
        telegramBot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                telegramBot.execute(processUpdate(update));
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public SendMessage processUpdate(Update update) {
        long id = update.message().chat().id();
        for (CommandInfo command : supportedCommands) {
            if (command.supports(update)) {
                inputHandler.addLastAction(id, command);
                return command.handle(update);
            }
        }
        CommandInfo lastCommand = inputHandler.checkLastAction(id);
        if (lastCommand != null) {
            return lastCommand.handle(update);
        }
        return new SendMessage(update.message().chat().id(), INVALID_COMMAND);
    }

    public void sendMessage(LinkUpdate update) {
        for (Long chatId: update.tgChatIds()) {
            telegramBot.execute(new SendMessage(chatId, update.description()));
        }
    }

    public void close() {
        telegramBot.removeGetUpdatesListener();
    }


}
