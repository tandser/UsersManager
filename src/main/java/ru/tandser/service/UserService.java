package ru.tandser.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.dao.UserDao;
import ru.tandser.domain.User;

import java.util.List;

@Service
public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public List<User> list(Integer offset, Integer maxResults) {
        return userDao.list(offset, maxResults);
    }

    @Transactional
    public Long count() {
        return userDao.count();
    }

    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    public void remove(int id) {
        userDao.remove(id);
    }

    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    public User get(int id) {
        return userDao.get(id);
    }

    @Transactional
    public List<User> selection(String name) {
        return userDao.selection(name);
    }

    /* Test method */

    @Transactional
    public void generate() {
        userDao.generate();
    }
}