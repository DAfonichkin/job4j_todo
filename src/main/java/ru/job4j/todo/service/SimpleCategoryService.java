package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class SimpleCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public SimpleCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean deleteById(int id) {
        return categoryRepository.deleteById(id);
    }

    @Override
    public Set<Category> findCategoriesById(Set<Integer> id) {
        return categoryRepository.findCategoriesById(id);
    }

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }
}
