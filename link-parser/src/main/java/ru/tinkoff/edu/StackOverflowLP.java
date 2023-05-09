package ru.tinkoff.edu;

import java.net.URL;

public class StackOverflowLP extends Parser {

    public StackOverflowLP(Parser nextParser) {
        super(nextParser);
    }

    @Override
    public ParsedObject parseLink(URL url) {
        if (url.getHost().equals("stackoverflow.com")) {
            String[] arr = url.getPath().split("/");
            if (arr.length > 2) {
                String section = arr[1];
                long id = Long.parseLong(arr[2]);
                return (section.equals("questions")) ? new StackOverflowQuestion(id) : null;
            }
        }
        return null;
    }
}
