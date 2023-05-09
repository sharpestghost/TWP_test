package ru.tinkoff.edu.domain.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tinkoff.edu.entity.Link;

import java.util.List;
import java.util.Optional;

public interface JpaLinkRepo extends JpaRepository<Link, Long> {
    @Query(value = "SELECT * FROM Link l where l.url = :url", nativeQuery = true)
    Optional<Link> findByLink(String url);
}
