package ru.tinkoff.edu;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class Parser {
    final Parser nextParser;

    Parser(Parser nextParser) {
        this.nextParser = nextParser;
    }

    abstract ParsedObject parseLink(URL url);

    public ParsedObject parse(URL url) {
        ParsedObject res = parseLink(url);
        if (res != null) {
            return res;
        }
        if (nextParser != null) {
            return nextParser.parse(url);
        }
        return null;
    }

    public static boolean isValid(String link) {
        try {
            new URL(link).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return true;
    }
}