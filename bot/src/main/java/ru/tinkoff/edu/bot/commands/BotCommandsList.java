package ru.tinkoff.edu.bot.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public enum BotCommandsList {
    START("/start", "registers a new user", new StartCommand()),
    LIST("/help", "prints a list of current commands", new ListCommand()),
    HELP("/list", "displays a list of tracking links", new ListCommand()),
    TRACK("/track", "starts tracking a link", new TrackCommand()),
    UNTRACK("/untrack", "stops tracking a link", new UntrackCommand());

    private static final Map<String, CommandInfo> COMMANDS = new HashMap<>();
    private final String name;
    private final String description;
    private final CommandInfo command;


    static {
        Arrays.stream(values()).forEach(v -> COMMANDS.put(v.name, v.command));
    }

    static Map<String, CommandInfo> getCommands() {
        return COMMANDS;
    }

    BotCommandsList(String name, String description, CommandInfo command) {
        this.name = name;
        this.command = command;
        this.description = description;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
