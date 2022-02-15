package by.epam.bartenderhelper.model.entity;

import java.util.List;

/**
 * The type Cocktail.
 */
public final class Cocktail extends AbstractDaoEntity {
    private final String name;
    private final String description;
    private final String recipe;
    private final Photo photo;
    private final long authorId;
    private final List<Long> reviews;
    private final List<Long> ingredients;

    private Cocktail(CocktailBuilder builder) {
        super(builder.cocktailId);
        this.name = builder.name;
        this.description = builder.description;
        this.recipe = builder.recipe;
        this.photo = builder.photo;
        this.authorId = builder.authorId;
        this.reviews = builder.reviews;
        this.ingredients = builder.ingredients;
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
     * Gets recipe.
     *
     * @return the recipe
     */
    public String getRecipe() {
        return recipe;
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
     * Gets author id.
     *
     * @return the author id
     */
    public long getAuthorId() {
        return authorId;
    }

    /**
     * Gets reviews.
     *
     * @return the reviews
     */
    public List<Long> getReviews() {
        return List.copyOf(reviews);
    }

    /**
     * Gets ingredients.
     *
     * @return the ingredients
     */
    public List<Long> getIngredients() {
        return List.copyOf(ingredients);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cocktail)) return false;
        if (!super.equals(o)) return false;

        Cocktail cocktail = (Cocktail) o;

        if (authorId != cocktail.authorId) return false;
        if (name != null ? !name.equals(cocktail.name) : cocktail.name != null) return false;
        if (description != null ? !description.equals(cocktail.description) : cocktail.description != null)
            return false;
        if (recipe != null ? !recipe.equals(cocktail.recipe) : cocktail.recipe != null) return false;
        if (photo != null ? !photo.equals(cocktail.photo) : cocktail.photo != null) return false;
        if (reviews != null ? !reviews.equals(cocktail.reviews) : cocktail.reviews != null) return false;
        return ingredients != null ? ingredients.equals(cocktail.ingredients) : cocktail.ingredients == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (int) (authorId ^ (authorId >>> 32));
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cocktail{");
        sb.append("cocktailId=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", recipe='").append(recipe).append('\'');
        sb.append(", photo=").append(photo);
        sb.append(", authorId=").append(authorId);
        sb.append(", comments=").append(reviews);
        sb.append(", ingredients=").append(ingredients);
        sb.append('}');
        return sb.toString();
    }

    /**
     * The type Cocktail builder.
     */
    public static class CocktailBuilder {
        private long cocktailId;
        private String name;
        private String description;
        private String recipe;
        private Photo photo;
        private long authorId;
        private List<Long> reviews;
        private List<Long> ingredients;

        /**
         * Cocktail id cocktail builder.
         *
         * @param cocktailId the cocktail id
         * @return the cocktail builder
         */
        public CocktailBuilder cocktailId(long cocktailId) {
            this.cocktailId = cocktailId;
            return this;
        }

        /**
         * Name cocktail builder.
         *
         * @param name the name
         * @return the cocktail builder
         */
        public CocktailBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Description cocktail builder.
         *
         * @param description the description
         * @return the cocktail builder
         */
        public CocktailBuilder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Recipe cocktail builder.
         *
         * @param recipe the recipe
         * @return the cocktail builder
         */
        public CocktailBuilder recipe(String recipe) {
            this.recipe = recipe;
            return this;
        }


        /**
         * Photo cocktail builder.
         *
         * @param photo the photo
         * @return the cocktail builder
         */
        public CocktailBuilder photo(Photo photo) {
            this.photo = photo;
            return this;
        }

        /**
         * Author id cocktail builder.
         *
         * @param authorId the author id
         * @return the cocktail builder
         */
        public CocktailBuilder authorId(long authorId) {
            this.authorId = authorId;
            return this;
        }

        /**
         * Comments cocktail builder.
         *
         * @param reviews the reviews
         * @return the cocktail builder
         */
        public CocktailBuilder comments(List<Long> reviews) {
            this.reviews = reviews;
            return this;
        }

        /**
         * Ingredients cocktail builder.
         *
         * @param ingredients the ingredients
         * @return the cocktail builder
         */
        public CocktailBuilder ingredients(List<Long> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        /**
         * Build cocktail.
         *
         * @return the cocktail
         */
        public Cocktail build() {
            return new Cocktail(this);
        }
    }
}
