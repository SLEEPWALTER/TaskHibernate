package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.buildSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                        "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(255), " +
                        "lastname VARCHAR(255)," +
                        "age TINYINT)").executeUpdate();
                transaction.commit();
            } catch (Exception ex) {
                if (transaction != null){
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
                transaction.commit();
            } catch (Exception ex) {
                if (transaction != null){
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = new User();
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);
                session.save(user);
                transaction.commit();
            } catch (Exception ex) {
                if (transaction != null){
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.get(User.class, id);
                if (user != null) {
                    session.delete(user);
                }
                transaction.commit();
            } catch (Exception ex) {
                if (transaction != null){
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.createQuery("DELETE FROM User").executeUpdate();
                transaction.commit();
            } catch (Exception ex) {
                if (transaction != null){
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }
    }
}
