package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.todo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class SimpleUserRepository {
    private final SessionFactory sf;

    public Optional<User> create(User user) {
        Session session = sf.openSession();
        Optional<User> rsl = Optional.empty();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            rsl = Optional.of(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User SET password = :fPassword, login = :fLogin WHERE id = :fId")
                    .setParameter("fPassword", user.getPassword())
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        List<User> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from User ORDER BY id ");
            rsl = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rsl;
    }

    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        List<User> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from User as u WHERE u.login like :fLogin");
            query.setParameter("fLogin", key);
            rsl = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rsl;
    }

    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Optional<User> rsl = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(
                    "from User as u where u.login = :fLogin", User.class);
            query.setParameter("fLogin", login);
            rsl = query.uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rsl;
    }
}