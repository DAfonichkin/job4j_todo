package ru.job4j.todo.repository;

import ru.job4j.todo.model.Priority;

import java.util.Collection;
import java.util.Optional;

public interface PriorityRepository {

    Optional<Priority> save(Priority priority);

    boolean deleteById(int id);

    Optional<Priority> findById(int id);

    Collection<Priority> findAll();

}