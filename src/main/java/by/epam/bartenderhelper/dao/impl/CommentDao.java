package by.epam.bartenderhelper.dao.impl;

import by.epam.bartenderhelper.dao.AbstractDao;
import by.epam.bartenderhelper.entity.Comment;

import java.util.List;

public class CommentDao extends AbstractDao<Comment> {
    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment findById(long id) {
        return null;
    }

    @Override
    public boolean create(Comment entity) {
        return false;
    }

    @Override
    public Comment update(Comment entity) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
