package base.dao;

import base.exception.UserException;
import base.model.User;

import java.util.List;

public interface UserDao {
    void insertUser(User user) throws UserException;

    List getAllUsers();

    User getUserById(Long id) throws UserException;

    void deleteUser(Long id) throws UserException;

    void updateUser(User user) throws UserException;

    User getUserByLogin(String login);
}
