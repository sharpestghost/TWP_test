package ru.tinkoff.edu.domain.jpa;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.entity.Chat;


@Repository
public interface JpaChatRepo extends JpaRepository<Chat, Long> {
    @Query(value="insert into chat (id, chatname) values (chat_id, chat_name) on conflict do nothing",
            nativeQuery = true)
    void insertChat(@Param("chat_id") Long id, @Param("chat_name") String chatName);
}
