package base.service;

import base.repository.UserRepository;
import base.exception.UserException;
import base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    /* Using DAO */
    //    @Autowired
//    private UserDao userDao;
//
//
//    @Override
//    public void add(User user) throws UserException {
//        userDao.insertUser(user);
//    }
//
//    @Override
//    public List getAllUsers() {
//        return userDao.getAllUsers();
//    }
//
//    @Override
//    public User getUserById(Long id) throws UserException {
//        return userDao.getUserById(id);
//    }
//
//    @Override
//    public void deleteUser(Long id) throws UserException {
//        userDao.deleteUser(id);
//    }
//
//    @Override
//    public void updateUser(User user) throws UserException {
//        userDao.updateUser(user);
//    }
//
//    @Override
//    public User getUserByLogin(String login) {
//        return userDao.getUserByLogin(login);
//    }

    /* Using Spring DATA */
    @Autowired
    private UserRepository userRepository;
    @Override
    public void add(User user) throws UserException {
        userRepository.save(user);
    }
    @Override
    public List getAllUsers() {
        return (List) userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow( UserException::new);
    }

    @Override
    public void deleteUser(Long id) throws UserException {
       userRepository.delete(getUserById(id));
    }

    @Override
    public void updateUser(User user) throws UserException {
       userRepository.save(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

}
