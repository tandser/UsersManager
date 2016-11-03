package ru.tandser.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.tandser.domain.User;

import java.util.List;
import java.util.Random;

@Repository
public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<User> list(Integer offset, Integer maxResults) {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createCriteria(User.class)
                .setFirstResult(offset != null ? offset : 0)
                .setMaxResults(maxResults != null ? maxResults : 10)
                .list();

        for (User user : list) {
            logger.info(user.toString());
        }

        return list;
    }

    public Long count() {
        Session session = sessionFactory.getCurrentSession();

        return (Long) session.createCriteria(User.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);

        logger.info(user != null ? user.toString() : null);
    }

    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if (user != null) {
            session.delete(user);
        }

        logger.info(user != null ? user.toString() : null);
    }

    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);

        logger.info(user != null ? user.toString() : null);
    }

    public User get(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        logger.info(user != null ? user.toString() : null);

        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> selection(String name) {
        Session session = sessionFactory.getCurrentSession();
        List<User> selection = session.createQuery("from User where name = '" + name + "'").list();

        for (User user : selection) {
            logger.info(user.toString());
        }

        return selection;
    }

    /* Test method */

    public void generate() {
        Session session = sessionFactory.getCurrentSession();
        Random rand = new Random();

        for (int i = 1; i <= 25; i++) {
            session.persist(new User("test_user_" + i, i, rand.nextBoolean()));
        }
    }
}