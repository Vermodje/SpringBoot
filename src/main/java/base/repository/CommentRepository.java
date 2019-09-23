package base.repository;

import base.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
