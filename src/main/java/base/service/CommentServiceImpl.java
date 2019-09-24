package base.service;

import base.exception.UserException;
import base.model.Comment;
import base.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public void add(Comment comment) throws UserException {
        commentRepository.save(comment);
    }

    @Override
    public List getAllComments() {
        return (List) commentRepository.findAll();
    }
}
