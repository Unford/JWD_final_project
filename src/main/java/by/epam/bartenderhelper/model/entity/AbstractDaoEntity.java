package by.epam.bartenderhelper.model.entity;

import java.io.Serializable;

/**
 * The type Abstract dao entity.
 */
public abstract class AbstractDaoEntity implements Serializable, Cloneable {
    /**
     * The Id.
     */
    protected long id;

    /**
     * Instantiates a new Abstract dao entity.
     *
     * @param id the id
     */
    AbstractDaoEntity(long id){
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
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
