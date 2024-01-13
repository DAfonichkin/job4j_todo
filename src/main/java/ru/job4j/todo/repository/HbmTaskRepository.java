package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmTaskRepository implements TaskRepository {

    private static final Logger LOGGER = Logger.getLogger(HbmTaskRepository.class);
    private final CrudRepository crudRepository;

    @Override
    public Optional<Task> save(Task task) {
        crudRepository.run(session -> session.save(task));
        return Optional.ofNullable(task);
    }

    @Override
    public Optional<Task> getById(int id) {
        return crudRepository.optional(
                "from Task as t where t.id = :fId",
                Task.class,
                Map.of("fId", id));
    }

    @Override
    public List<Task> findAll() {
        return crudRepository.query("from Task as t", Task.class);
    }

    @Override
    public List<Task> findByDone(boolean isDone) {
        return crudRepository.query(
                "from Task as i where i.done = :fDone",
                Task.class,
                Map.of("fDone", isDone));
    }

    @Override
    public boolean update(Task task) {
        boolean rsl = false;
        try {
            crudRepository.run(session -> session.merge(task));
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("Exception during delete user", e);
        }
        return rsl;
    }

    @Override
    public boolean setDone(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(
                    "UPDATE Task SET done = true WHERE id = :fId",
                    Map.of("fId", id));
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("Exception during delete user", e);
        }
        return rsl;
    }

    @Override
    public boolean delete(Task task) {
        boolean rsl = false;
        try {
            crudRepository.run(
                    "delete from Task where id = :fId",
                    Map.of("fId", task.getId()));
                    rsl = true;
        } catch (Exception e) {
            LOGGER.error("Exception during delete user", e);
        }
        return rsl;
    }

}
