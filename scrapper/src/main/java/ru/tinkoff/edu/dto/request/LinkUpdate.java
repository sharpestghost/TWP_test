package ru.tinkoff.edu.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.List;

public record LinkUpdate(long id, URI link, String description, List<Long> tgChatIds) {
}
