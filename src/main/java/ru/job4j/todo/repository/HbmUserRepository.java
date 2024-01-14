package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.*;

@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {
    private final CrudRepository crudRepository;
    private static final Logger LOGGER = Logger.getLogger(HbmUserRepository.class);

    @Override
    public boolean deleteById(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(
                    "delete from User where id = :fId",
                    Map.of("fId", id));
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("Exception during delete user", e);
        }
        return rsl;
    }

    @Override
    public Optional<User> save(User user) {
        Optional<User> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.save(user));
            rsl = Optional.of(user);
        } catch (Exception e) {
            LOGGER.error("Exception during save user", e);
        }
        return rsl;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String email, String password) {
        return crudRepository.optional(
                "from User as u where u.login = :fLogin and u.password = :fPassword",
                User.class,
                Map.of("fLogin", email, "fPassword", password));
    }


    public Optional<User> findById(int userId) {
        return crudRepository.optional(
                "from User as u where u.id = :fId",
                User.class,
                Map.of("fId", userId));
    }

    @Override
    public Collection<User> findAll() {
        return crudRepository.query("from User as u", User.class);
    }

}