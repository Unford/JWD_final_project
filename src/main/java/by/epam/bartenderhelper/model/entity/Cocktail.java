package by.epam.bartenderhelper.model.entity;

import java.util.List;

public final class Cocktail extends AbstractDaoEntity {
    private final String name;
    private final String description;
    private final String recipe;
    private final boolean verified;
    private final Photo photo;
    private final long authorId;
    private final List<Long> reviews;
    private final List<Long> ingredients;

    private Cocktail(CocktailBuilder builder) {
        super(builder.cocktailId);
        this.name = builder.name;
        this.description = builder.description;
        this.recipe = builder.recipe;
        this.verified = builder.verified;
        this.photo = builder.photo;
        this.authorId = builder.authorId;
        this.reviews = builder.reviews;
        this.ingredients = builder.ingredients;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRecipe() {
        return recipe;
    }

    public boolean isVerified() {
        return verified;
    }

    public Photo getPhoto() {
        return photo;
    }

    public long getAuthorId() {
        return authorId;
    }

    public List<Long> getReviews() {
        return List.copyOf(reviews);
    }

    public List<Long> getIngredients() {
        return List.copyOf(ingredients);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cocktail)) return false;
        if (!super.equals(o)) return false;

        Cocktail cocktail = (Cocktail) o;

        if (verified != cocktail.verified) return false;
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
        result = 31 * result + (verified ? 1 : 0);
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
        sb.append(", verified=").append(verified);
        sb.append(", photo=").append(photo);
        sb.append(", authorId=").append(authorId);
        sb.append(", comments=").append(reviews);
        sb.append(", ingredients=").append(ingredients);
        sb.append('}');
        return sb.toString();
    }

    public static class CocktailBuilder {
        private long cocktailId;
        private String name;
        private String description;
        private String recipe;
        private boolean verified;
        private Photo photo;
        private long authorId;
        private List<Long> reviews;
        private List<Long> ingredients;

        public CocktailBuilder cocktailId(long cocktailId){
            this.cocktailId = cocktailId;
            return this;
        }

        public CocktailBuilder name(String name){
            this.name = name;
            return this;
        }

        public CocktailBuilder description(String description){
            this.description = description;
            return this;
        }

        public CocktailBuilder recipe(String recipe){
            this.recipe = recipe;
            return this;
        }

        public CocktailBuilder verified(boolean verified){
            this.verified = verified;
            return this;
        }

        public CocktailBuilder photo(Photo photo){
            this.photo = photo;
            return this;
        }

        public CocktailBuilder authorId(long authorId){
            this.authorId = authorId;
            return this;
        }

        public CocktailBuilder comments(List<Long> reviews){
            this.reviews = reviews;
            return this;
        }

        public CocktailBuilder ingredients(List<Long> ingredients){
            this.ingredients = ingredients;
            return this;
        }

        public Cocktail build() {
            return new Cocktail(this);
        }
    }
}
