package ru.tinkoff.edu.bot.logic;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public final class LinkProcessing {

    private static final Set<URI> URIS = new HashSet<>();

    private LinkProcessing() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void add(URI link) throws RuntimeException {
        if (URIS.contains(link)) {
            throw new RuntimeException("This link is already added");
        }
        URIS.add(link);
    }

    public static void remove(URI link) throws RuntimeException {
        if (!URIS.contains(link)) {
            throw new RuntimeException("This link is not in the tracked list");
        }
        URIS.remove(link);
    }

    public static URI validate(String msg) throws RuntimeException {
        try {
            return URI.create(msg);
        } catch (Exception e) {
            throw new RuntimeException("Invalid link");
        }
    }

    public static Set<URI> getUris() {
        return URIS;
    }
}
