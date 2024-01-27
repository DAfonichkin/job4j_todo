package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPriorityRepository implements PriorityRepository {
    private final CrudRepository crudRepository;
    private static final Logger LOGGER = Logger.getLogger(HbmPriorityRepository.class);

    @Override
    public boolean deleteById(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(
                    "delete from Priority where id = :fId",
                    Map.of("fId", id));
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("Exception during delete priority", e);
        }
        return rsl;
    }

    @Override
    public Optional<Priority> save(Priority priority) {
        Optional<Priority> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.save(priority));
            rsl = Optional.of(priority);
        } catch (Exception e) {
            LOGGER.error("Exception during save priority", e);
        }
        return rsl;
    }


    public Optional<Priority> findById(int priorityId) {
        return crudRepository.optional(
                "from Priority as p where p.id = :fId",
                Priority.class,
                Map.of("fId", priorityId));
    }

    @Override
    public Collection<Priority> findAll() {
        return crudRepository.query("from Priority as p", Priority.class);
    }

}