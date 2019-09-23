package base.service;

import base.exception.UserException;
import base.model.Comment;
import base.model.User;

import java.util.List;

public interface CommentService {
    void add(Comment comment) throws UserException;

    /*List getAllUsers();

    User getUserById(Long id) throws UserException;

    void deleteUser(Long id) throws UserException;

    void updateUser(User user) throws UserException;

    User getUserByLogin(String login);*/
}
