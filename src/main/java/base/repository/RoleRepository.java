package base.repository;

import base.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleRepository extends CrudRepository<Role, Long> {
}
