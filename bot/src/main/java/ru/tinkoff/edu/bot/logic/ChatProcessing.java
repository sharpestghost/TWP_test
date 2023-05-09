package ru.tinkoff.edu.bot.logic;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatProcessing {
    private static final Set<String> usernames = new HashSet<>();
    private static final Map<Long, String> users = new HashMap<>();

    public static void add(Long id, String name) throws RuntimeException {
        if (!isOccupied(name)) {
            users.put(id, name);
            usernames.add(name);
        } else {
            throw new RuntimeException("User with this name is already added");
        }
    }

    public static boolean isOccupied(String name) throws RuntimeException {
        return usernames.contains(name);
    }
}
