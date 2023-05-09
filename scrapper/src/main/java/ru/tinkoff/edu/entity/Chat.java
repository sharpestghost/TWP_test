package ru.tinkoff.edu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat")
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
