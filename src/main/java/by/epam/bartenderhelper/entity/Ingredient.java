package by.epam.bartenderhelper.entity;

import java.math.BigDecimal;

public final class Ingredient extends AbstractDaoEntity {
    private final String name;
    private final String description;
    private final boolean verified;
    private final BigDecimal price;
    private final BigDecimal calorie;
    private final Photo photo;
    private final Measure measure;
    private final BigDecimal amount;

    private Ingredient(IngredientBuilder builder){
        super(builder.ingredientId);
        this.name = builder.name;
        this.description = builder.description;;
        this.verified = builder.verified;
        this.price = builder.price;
        this.calorie = builder.calorie;
        this.photo = builder.photo;
        this.measure = builder.measure;
        this.amount = builder.amount;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVerified() {
        return verified;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getCalorie() {
        return calorie;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Measure getMeasure() {
        return measure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        if (!super.equals(o)) return false;

        Ingredient that = (Ingredient) o;

        if (verified != that.verified) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (calorie != null ? !calorie.equals(that.calorie) : that.calorie != null) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
        if (measure != null ? !measure.equals(that.measure) : that.measure != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (verified ? 1 : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (calorie != null ? calorie.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ingredient{");
        sb.append("ingredientId=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", verified=").append(verified);
        sb.append(", price=").append(price);
        sb.append(", calorie=").append(calorie);
        sb.append(", photo=").append(photo);
        sb.append(", measure=").append(measure);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    public static class IngredientBuilder {
        private long ingredientId;
        private String name;
        private String description;
        private boolean verified;
        private BigDecimal price;
        private BigDecimal calorie;
        private Photo photo;
        private Measure measure;
        private BigDecimal amount;

        public IngredientBuilder ingredientId(long ingredientId){
            this.ingredientId = ingredientId;
            return this;
        }

        public IngredientBuilder name(String name){
            this.name = name;
            return this;
        }

        public IngredientBuilder description(String description){
            this.description = description;
            return this;
        }

        public IngredientBuilder verified(boolean verified){
            this.verified = verified;
            return this;
        }

        public IngredientBuilder price(BigDecimal price){
            this.price = price;
            return this;
        }

        public IngredientBuilder calorie(BigDecimal calorie){
            this.calorie = calorie;
            return this;
        }

        public IngredientBuilder photo(Photo photo){
            this.photo = photo;
            return this;
        }

        public IngredientBuilder measure(Measure measure){
            this.measure = measure;
            return this;
        }

        public IngredientBuilder amount(BigDecimal amount){
            this.amount = amount;
            return this;
        }

        public Ingredient build() {
            return new Ingredient(this);
        }
    }
}
