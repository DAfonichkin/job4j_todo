package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmTaskRepository implements TaskRepository {

    private final SessionFactory sf;
    private static final Logger LOGGER = Logger.getLogger(HbmTaskRepository.class);

    @Override
    public Optional<Task> save(Task task) {
        Session session = sf.openSession();
        Optional<Task> rsl = Optional.empty();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
            rsl = Optional.of(task);
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Exception during saving new task", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public Optional<Task> getById(int id) {
        Session session = sf.openSession();
        Optional<Task> rsl = Optional.empty();
        try {
            session.beginTransaction();
            Query<Task> query = session.createQuery(
                    "from Task as t where t.id = :fId", Task.class);
            query.setParameter("fId", id);
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

    @Override
    public List<Task> findAll() {
        Session session = sf.openSession();
        List<Task> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            rsl = session.createQuery("from ru.job4j.todo.model.Task", Task.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Exception during finding new tasks", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public List<Task> findByDone(boolean isDone) {
        Session session = sf.openSession();
        List<Task> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Task> query = session.createQuery(
                    "from Task as i where i.done = :fDone", Task.class);
            query.setParameter("fDone", isDone);
            rsl = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Exception during finding new tasks", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public boolean update(Task task) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            session.update(task);
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Exception during updating task", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public boolean setDone(int id) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            Query<Task> query = session.createQuery(
                    "UPDATE Task SET done = true WHERE id = :fId");
            query.setParameter("fId", id).executeUpdate();
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Exception during updating task", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public boolean delete(Task task) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            session.delete(task);
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("Exception during deleting task", e);
        } finally {
            session.close();
        }
        return rsl;
    }

}
