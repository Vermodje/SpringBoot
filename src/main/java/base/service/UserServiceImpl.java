package base.service;

import base.exception.UserException;
import base.model.User;
import base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

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
        return userRepository.findById(id).orElseThrow(UserException::new);
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
