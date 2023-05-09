package ru.tinkoff.edu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url", unique = true, nullable = false)
    private String URL;

    @Column(name = "linkname")
    private String linkName;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime lastUpdateDate;

    @Column(name = "answer_count")
    private Integer answerCount;
}
