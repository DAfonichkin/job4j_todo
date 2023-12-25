package ru.job4j.todo.repository;

import ru.job4j.todo.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

    Optional<User> save(User user);

    Optional<User> findByLoginAndPassword(String email, String password);

    boolean deleteById(int id);

    Optional<User> findById(int id);

    Collection<User> findAll();

}