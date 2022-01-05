package by.epam.bartenderhelper.dao.impl;

import by.epam.bartenderhelper.dao.AbstractDao;
import by.epam.bartenderhelper.entity.Measure;

import java.util.List;

public class MeasureDao extends AbstractDao<Measure> {
    @Override
    public List<Measure> findAll() {
        return null;
    }

    @Override
    public Measure findById(long id) {
        return null;
    }

    @Override
    public boolean create(Measure entity) {
        return false;
    }

    @Override
    public Measure update(Measure entity) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
