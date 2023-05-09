package ru.tinkoff.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.converter.ResponseConverter;
import ru.tinkoff.edu.dto.request.AddLinkRequest;
import ru.tinkoff.edu.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.dto.response.LinkResponse;
import ru.tinkoff.edu.dto.response.ListLinksResponse;
import ru.tinkoff.edu.service.LinkChatService;
import ru.tinkoff.edu.service.LinkService;

@RestController
@RequestMapping(name = "/links")
@RequiredArgsConstructor
public class LinksController {

    private final LinkService linkService;
    private final LinkChatService linkChatService;

    @GetMapping
    public ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") long tgChatId) {
        return ResponseConverter.getListLinksResponse(linkService.listAll(tgChatId));
    }

    public LinkResponse addLink(@RequestHeader("Tg-Chat-Id") long tgChatId, @RequestBody AddLinkRequest addLinkRequest) {
        return ResponseConverter.getLinkResponse(linkService.add(tgChatId, addLinkRequest.link()));
    }

    public LinkResponse removeLink(@RequestHeader("Tg-Chat-Id") long tgChatId, @RequestBody RemoveLinkRequest removeLinkRequest) {
        return ResponseConverter.getLinkResponse(linkChatService.untrack(tgChatId, removeLinkRequest.link().toString()));
    }
}
