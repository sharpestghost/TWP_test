package ru.tinkoff.edu.converter;

import org.springframework.stereotype.Component;
import ru.tinkoff.edu.dto.response.LinkResponse;
import ru.tinkoff.edu.dto.response.ListLinksResponse;
import ru.tinkoff.edu.entity.Link;

import java.net.URI;
import java.util.List;

@Component
public class ResponseConverter {
    public static LinkResponse getLinkResponse(Link link) {
        return new LinkResponse(link.getId(), URI.create(link.getURL()));
    }

    public static ListLinksResponse getListLinksResponse(List<Link> links) {
        return new ListLinksResponse(links.stream().map(ResponseConverter::getLinkResponse).
                toArray(LinkResponse[]::new), links.size());
    }
}