package by.epam.bartenderhelper.model.entity;

/**
 * The type Measure.
 */
public final class Measure extends AbstractDaoEntity {
    private String name;

    /**
     * Instantiates a new Measure.
     *
     * @param id   the id
     * @param name the name
     */
    public Measure(long id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * Instantiates a new Measure.
     *
     * @param id the id
     */
    public Measure(long id) {
        super(id);
    }

    /**
     * Instantiates a new Measure.
     */
    public Measure() {
        super(0);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measure)) return false;
        if (!super.equals(o)) return false;

        Measure measure = (Measure) o;

        return name != null ? name.equals(measure.name) : measure.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Measure{");
        sb.append("measureId=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
