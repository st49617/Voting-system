package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.UserDao;
import cz.upce.votingsystemapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void addUser(User user) {
        userDao.save(user);
    }

    public User getUser(Long id) {
        Optional<User> foundUser = userDao.findById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();
        } else {
            return null;
        }
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public void deleteUser(Long id) {
        Optional<User> foundUser = userDao.findById(id);
        if (foundUser.isPresent()) {
            userDao.delete(foundUser.get());
        }

    }

}
