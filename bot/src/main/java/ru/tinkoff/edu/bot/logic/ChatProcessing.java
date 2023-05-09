package ru.tinkoff.edu.bot.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ChatProcessing {
    private static final Set<String> USERNAMES = new HashSet<>();
    private static final Map<Long, String> USERS = new HashMap<>();

    private ChatProcessing() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void add(Long id, String name) throws RuntimeException {
        if (!isOccupied(name)) {
            USERS.put(id, name);
            USERNAMES.add(name);
        } else {
            throw new RuntimeException("User with this name is already added");
        }
    }

    public static boolean isOccupied(String name) throws RuntimeException {
        return USERNAMES.contains(name);
    }
}
