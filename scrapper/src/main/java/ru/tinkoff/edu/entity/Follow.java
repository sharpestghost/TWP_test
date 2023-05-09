package ru.tinkoff.edu.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "link_chat")
public class Follow {

    @EmbeddedId
    private LinkChat id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("chat_id")
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("link_id")
    private Link link;

    public Follow(Long linkId, Long chatId) {
        this.id = new LinkChat(chatId, linkId);
    }

    public Follow(Link link, Chat chat) {
        this(chat.getId(), link.getId());
        this.chat = chat;
        this.link = link;
    }

    public Long getChatId() {
        return id.getChat_id();
    }

    public Long getLinkId() {
        return id.getLink_id();
    }
}