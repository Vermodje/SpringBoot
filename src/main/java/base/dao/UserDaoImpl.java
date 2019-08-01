package base.dao;

import base.exception.UserException;
import base.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void insertUser(User user) throws UserException {
        manager.persist(user);
    }

    @Override
    public List getAllUsers() {
        return manager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) throws UserException {
        return manager.find(User.class, id);
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        manager.remove(getUserById(id));
    }

    @Override
    public void updateUser(User user) throws UserException {
        manager.merge(user);
    }

    @Override
    public User getUserByLogin(String login) {
        Query query = manager.createQuery("from User u where u.login = :login");
        query.setParameter("login", login);
        User user = (User) query.getSingleResult();
        return user;
    }
}
