package by.epam.bartenderhelper.entity;

import java.io.Serializable;

public abstract class AbstractDaoEntity implements Serializable, Cloneable {
    protected final long id;

    protected AbstractDaoEntity(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDaoEntity)) return false;

        AbstractDaoEntity abstractDaoEntity = (AbstractDaoEntity) o;

        return id == abstractDaoEntity.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
