package base.service;

import base.exception.UserException;
import base.model.News;

import java.util.List;

public interface NewsService {
    void add(News news) throws UserException;

    List getAllNews();

    News getNewsById(Long id) throws UserException;

    void deleteNews(Long id) throws UserException;

    void updateNews(News news) throws UserException;

}
