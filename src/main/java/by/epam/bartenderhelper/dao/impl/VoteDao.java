package by.epam.bartenderhelper.dao.impl;

import by.epam.bartenderhelper.dao.AbstractDao;
import by.epam.bartenderhelper.entity.Vote;

import java.util.List;

public class VoteDao extends AbstractDao<Vote> {
    @Override
    public List<Vote> findAll() {
        return null;
    }

    @Override
    public Vote findById(long id) {
        return null;
    }

    @Override
    public boolean create(Vote entity) {
        return false;
    }

    @Override
    public Vote update(Vote entity) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
