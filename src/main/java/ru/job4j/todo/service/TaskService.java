package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<Task> save(Task task);

    Optional<Task> getById(int id);

    List<Task> findAll();

    List<Task> findNew();

    List<Task> findDone();

    boolean update(Task task);

    boolean delete(Task task);
}
