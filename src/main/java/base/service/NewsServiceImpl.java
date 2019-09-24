package base.service;

import base.exception.UserException;
import base.model.News;
import base.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository repository;

    @Override
    public void add(News news) throws UserException {
        repository.save(news);
    }

    @Override
    public List getAllNews() {
        return (List) repository.findAll();
    }

    @Override
    public News getNewsById(Long id) throws UserException {
        return repository.findById(id).orElseThrow(UserException::new);
    }

    @Override
    public void deleteNews(Long id) throws UserException {
        repository.delete(getNewsById(id));
    }

    @Override
    public void updateNews(News news) throws UserException {
        repository.save(news);
    }
}
