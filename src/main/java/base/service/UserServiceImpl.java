package base.service;


import base.dao.UserDao;
import base.exception.UserException;
import base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public void add(User user) throws UserException {
        userDao.insertUser(user);
    }

    @Override
    public List getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) throws UserException {
        return userDao.getUserById(id);
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        userDao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) throws UserException {
        userDao.updateUser(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }


}
