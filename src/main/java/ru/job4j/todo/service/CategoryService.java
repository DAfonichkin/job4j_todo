package ru.job4j.todo.service;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Priority;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    Optional<Category> save(Category category);

    boolean deleteById(int id);

    Set<Category> findCategoriesById(Set<Integer> id);

    Collection<Category> findAll();

}
