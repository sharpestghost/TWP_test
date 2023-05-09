package ru.tinkoff.edu.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="chat")
public class Chat {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "chatname")
    private String chatName;
    @Column(name = "created_at")
    private OffsetDateTime createdDate;
    @Column(name = "updated_at")
    private OffsetDateTime lastUpdateDate;
}