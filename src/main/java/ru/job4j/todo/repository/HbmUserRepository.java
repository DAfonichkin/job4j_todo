package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {
    private final SessionFactory sf;
    private static final Logger LOGGER = Logger.getLogger(HbmUserRepository.class);

    @Override
    public boolean deleteById(int id) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE User WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Exception during delete user", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public Optional<User> save(User user) {
        Session session = sf.openSession();
        Optional<User> rsl = Optional.empty();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            rsl = Optional.of(user);
        } catch (Exception e) {
                session.getTransaction().rollback();
            LOGGER.error("Exception during save user", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String email, String password) {
        Session session = sf.openSession();
        Optional<User> rsl = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(
                    "from User as u where u.login = :fLogin and u.password = :fPassword",
                    User.class);
            query.setParameter("fLogin", email);
            query.setParameter("fPassword", password);
            rsl = query.uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Exception during find user", e);
        } finally {
            session.close();
        }
        return rsl;
    }


    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        Optional<User> rsl = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(
                    "from User as u where u.id = :fId", User.class);
            query.setParameter("fId", userId);
            rsl = query.uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Exception during find user", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public Collection<User> findAll() {
        Session session = sf.openSession();
        List<User> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from User");
            rsl = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Exception during find user", e);
        } finally {
            session.close();
        }
        return rsl;
    }


}