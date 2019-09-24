package base.repository;

import base.model.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NewsRepository extends CrudRepository<News, Long> {
}
