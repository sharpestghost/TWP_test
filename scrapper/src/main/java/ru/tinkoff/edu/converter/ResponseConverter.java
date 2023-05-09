package ru.tinkoff.edu.converter;

import java.net.URI;
import java.util.List;
import ru.tinkoff.edu.dto.response.LinkResponse;
import ru.tinkoff.edu.dto.response.ListLinksResponse;
import ru.tinkoff.edu.entity.Link;


public final class ResponseConverter {

    private ResponseConverter() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static LinkResponse getLinkResponse(Link link) {
        return new LinkResponse(link.getId(), URI.create(link.getUrl()));
    }

    public static ListLinksResponse getListLinksResponse(List<Link> links) {
        return new ListLinksResponse(links.stream().map(ResponseConverter::getLinkResponse)
                .toArray(LinkResponse[]::new), links.size());
    }
}
