package ru.tinkoff.edu.scrapperlink.dto.request;

public record LinkUpdate(long id, String url, String description, long[] tgChatIds) {
}
