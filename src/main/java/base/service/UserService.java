package base.service;

import base.exception.UserException;
import base.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void add(User user) throws UserException;

    List getAllUsers();

    User getUserById(Long id) throws UserException;

    void deleteUser(Long id) throws UserException;

    void updateUser(User user) throws UserException;

    User getUserByLogin(String login);
}
