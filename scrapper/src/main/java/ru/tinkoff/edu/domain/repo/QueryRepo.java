package ru.tinkoff.edu.domain.repo;

import java.util.List;

public interface QueryRepo<T> {
    List<T> findAll();
}
