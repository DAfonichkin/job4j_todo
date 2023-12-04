package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Optional<Task> save(Task task);

    List<Task> findAll();

    List<Task> findNew();

    List<Task> findDone();

    boolean update(Task task);

    boolean delete(Task task);

}
