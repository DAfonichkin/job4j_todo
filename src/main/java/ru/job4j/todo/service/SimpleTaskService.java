package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getById(int id) {
        return taskRepository.getById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findByDone(boolean isDone) {
        return taskRepository.findByDone(isDone);
    }

    @Override
    public boolean update(Task task) {
        return taskRepository.update(task);
    }

    @Override
    public boolean setDone(int id) {
        return taskRepository.setDone(id);
    }

    @Override
    public boolean delete(Task task) {
        return taskRepository.delete(task);
    }
}
