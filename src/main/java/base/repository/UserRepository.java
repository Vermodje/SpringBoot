package base.repository;

import base.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "from User u where u.login = :login")
    User getUserByLogin(String login);
}
