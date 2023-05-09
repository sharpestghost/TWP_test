package ru.tinkoff.edu;

import java.net.MalformedURLException;
import java.net.URL;

public class LinkParser {
    public static ParsedObject parseLink(String link) {
        try {
            URL url = new URL(link);
            Parser parser = new StackOverflowLP(new GithubLP(null));
            return parser.parse(url);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}