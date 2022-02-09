package by.epam.bartenderhelper.model.entity;

import java.math.BigDecimal;

/**
 * The type Ingredient.
 */
public final class Ingredient extends AbstractDaoEntity {
    private final String name;
    private final String description;
    private final boolean verified;
    private final BigDecimal price;
    private final long calorie;
    private final Photo photo;
    private final Measure measure;
    private final double amount;

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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Is verified boolean.
     *
     * @return the boolean
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Gets calorie.
     *
     * @return the calorie
     */
    public long getCalorie() {
        return calorie;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * Gets measure.
     *
     * @return the measure
     */
    public Measure getMeasure() {
        return measure;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        if (!super.equals(o)) return false;

        Ingredient that = (Ingredient) o;

        if (verified != that.verified) return false;
        if (calorie != that.calorie) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
        return measure != null ? measure.equals(that.measure) : that.measure == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (verified ? 1 : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) (calorie ^ (calorie >>> 32));
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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

    /**
     * The type Ingredient builder.
     */
    public static class IngredientBuilder {
        private long ingredientId;
        private String name;
        private String description;
        private boolean verified;
        private BigDecimal price;
        private long calorie;
        private Photo photo;
        private Measure measure;
        private double amount;

        /**
         * Ingredient id ingredient builder.
         *
         * @param ingredientId the ingredient id
         * @return the ingredient builder
         */
        public IngredientBuilder ingredientId(long ingredientId){
            this.ingredientId = ingredientId;
            return this;
        }

        /**
         * Name ingredient builder.
         *
         * @param name the name
         * @return the ingredient builder
         */
        public IngredientBuilder name(String name){
            this.name = name;
            return this;
        }

        /**
         * Description ingredient builder.
         *
         * @param description the description
         * @return the ingredient builder
         */
        public IngredientBuilder description(String description){
            this.description = description;
            return this;
        }

        /**
         * Verified ingredient builder.
         *
         * @param verified the verified
         * @return the ingredient builder
         */
        public IngredientBuilder verified(boolean verified){
            this.verified = verified;
            return this;
        }

        /**
         * Price ingredient builder.
         *
         * @param price the price
         * @return the ingredient builder
         */
        public IngredientBuilder price(BigDecimal price){
            this.price = price;
            return this;
        }

        /**
         * Calorie ingredient builder.
         *
         * @param calorie the calorie
         * @return the ingredient builder
         */
        public IngredientBuilder calorie(long calorie){
            this.calorie = calorie;
            return this;
        }

        /**
         * Photo ingredient builder.
         *
         * @param photo the photo
         * @return the ingredient builder
         */
        public IngredientBuilder photo(Photo photo){
            this.photo = photo;
            return this;
        }

        /**
         * Measure ingredient builder.
         *
         * @param measure the measure
         * @return the ingredient builder
         */
        public IngredientBuilder measure(Measure measure){
            this.measure = measure;
            return this;
        }

        /**
         * Amount ingredient builder.
         *
         * @param amount the amount
         * @return the ingredient builder
         */
        public IngredientBuilder amount(double amount){
            this.amount = amount;
            return this;
        }

        /**
         * Build ingredient.
         *
         * @return the ingredient
         */
        public Ingredient build() {
            return new Ingredient(this);
        }
    }
}
