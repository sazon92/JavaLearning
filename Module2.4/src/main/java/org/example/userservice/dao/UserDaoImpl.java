package org.example.userservice.dao;

import org.example.userservice.entity.User;
import org.example.userservice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void create(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            logger.info("Пользователь успешно создан: ID " + user.getId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Ошибка при создании пользователя", e);
        }
    }

    @Override
    public User read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            if (user != null) {
                logger.info("Пользователь успешно получен: ID " + id);
            } else {
                logger.info("Пользователь с ID " + id + " не найден");
            }
            return user;
        } catch (Exception e) {
            logger.error("Ошибка при получении пользователя с ID " + id, e);
            return null;
        }
    }

    @Override
    public void update(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            logger.info("Пользователь успешно обновлен: ID " + user.getId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Ошибка при обновлении пользователя с ID " + user.getId(), e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                tx.commit();
                logger.info("Пользователь успешно удален: ID " + id);
            } else {
                logger.info("Попытка удаления несуществующего пользователя: ID " + id);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Ошибка при удалении пользователя с ID " + id, e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("from User", User.class).list();
            logger.info("Успешно получен список пользователей. Найдено: " + users.size() + " пользователей");
            return users;
        } catch (Exception e) {
            logger.error("Ошибка при получении списка пользователей", e);
            return null;
        }
    }
}