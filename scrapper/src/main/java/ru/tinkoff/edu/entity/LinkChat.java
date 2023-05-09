package ru.tinkoff.edu.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LinkChat implements Serializable {
    private Long chat_id;
    private Long link_id;
}