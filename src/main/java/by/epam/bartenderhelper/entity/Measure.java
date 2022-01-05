package by.epam.bartenderhelper.entity;

public final class Measure extends AbstractDaoEntity {
    private final String name;

    public Measure(long id, String name){
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
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
