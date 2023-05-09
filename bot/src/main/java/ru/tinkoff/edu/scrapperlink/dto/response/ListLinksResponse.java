package ru.tinkoff.edu.scrapperlink.dto.response;

import ru.tinkoff.edu.scrapperlink.dto.response.LinkResponse;

public record ListLinksResponse(LinkResponse[] links, int size) {
}
