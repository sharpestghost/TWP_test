package ru.tinkoff.edu.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String url;

    @Column(name = "linkname")
    private String linkName;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime lastUpdateDate;

    @Column(name = "answer_count")
    private Integer answerCount;
}
