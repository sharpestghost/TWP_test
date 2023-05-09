package ru.tinkoff.edu;

import java.net.URL;

public class GithubLP extends Parser {

    public GithubLP (Parser nextParser) {
        super(nextParser);
    }

    @Override
    public ParsedObject parseLink(URL url) {
        if (url.getHost().equals("github.com")) {
            String[] arr = url.getPath().split("/");
            if (arr.length > 2) {
                String user = arr[1];
                String repo = arr[2];
                return new GithubRepo(user, repo);
            }
            else return null;
        }
        else return null;
    }
}

