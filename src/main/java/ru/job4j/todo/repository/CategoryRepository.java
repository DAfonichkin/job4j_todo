package ru.job4j.todo.repository;

import ru.job4j.todo.model.Category;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository {

    Optional<Category> save(Category category);

    boolean deleteById(int id);

    Collection<Category> findAll();

    Set<Category> findCategoriesById(Set<Integer> id);

}
