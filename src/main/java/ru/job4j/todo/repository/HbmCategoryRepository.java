package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmCategoryRepository implements CategoryRepository {
    private final CrudRepository crudRepository;
    private static final Logger LOGGER = Logger.getLogger(HbmCategoryRepository.class);

    @Override
    public boolean deleteById(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(
                    "delete from Category where id = :fId",
                    Map.of("fId", id));
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("Exception during delete category", e);
        }
        return rsl;
    }

    @Override
    public Optional<Category> save(Category category) {
        Optional<Category> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.save(category));
            rsl = Optional.of(category);
        } catch (Exception e) {
            LOGGER.error("Exception during save category", e);
        }
        return rsl;
    }


    public Optional<Category> findById(int categoryId) {
        return crudRepository.optional(
                "from Category as p where p.id = :fId",
                Category.class,
                Map.of("fId", categoryId));
    }

    @Override
    public Set<Category> findCategoriesById(Set<Integer> id) {
        return new HashSet<>(crudRepository.query(
                "from Category WHERE id in :categories", Category.class,
                Map.of("categories", id)));
    }

    @Override
    public Collection<Category> findAll() {
        return crudRepository.query("from Category as p", Category.class);
    }

}