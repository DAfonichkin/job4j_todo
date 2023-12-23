package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Optional<Task> save(Task task);

    Optional<Task> getById(int id);

    List<Task> findAll();

    List<Task> findByDone(boolean isDone);

    boolean update(Task task);

    boolean setDone(int id);

    boolean delete(Task task);

}
