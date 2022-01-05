package by.epam.bartenderhelper.dao.impl;

import by.epam.bartenderhelper.dao.AbstractDao;
import by.epam.bartenderhelper.entity.Photo;

import java.util.List;

public class PhotoDao extends AbstractDao<Photo> {
    @Override
    public List<Photo> findAll() {
        return null;
    }

    @Override
    public Photo findById(long id) {
        return null;
    }

    @Override
    public boolean create(Photo entity) {
        return false;
    }

    @Override
    public Photo update(Photo entity) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
