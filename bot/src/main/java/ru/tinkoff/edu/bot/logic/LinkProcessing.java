package ru.tinkoff.edu.bot.logic;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class LinkProcessing {
    private static final Set<URI> links = new HashSet<>();

    public static void add(URI link) throws RuntimeException {
        if (links.contains(link)) {
            throw new RuntimeException("This link is already added");
        }
        links.add(link);
    }

    public static void remove(URI link) throws RuntimeException {
        if (!links.contains(link)) {
            throw new RuntimeException("This link is not in the tracked list");
        }
        links.remove(link);
    }

    public static URI validate(String msg) throws RuntimeException {
        try {
            return URI.create(msg);
        } catch (Exception e) {
            throw new RuntimeException("Invalid link");
        }
    }

    public static Set<URI> getLinks() {
        return links;
    }
}
