package by.epam.bartenderhelper.entity;

import java.math.BigDecimal;
import java.util.List;

public final class Cocktail extends AbstractDaoEntity {
    private final String name;
    private final String description;
    private final String recipe;
    private final boolean verified;
    private final Photo photo;
    private final User author;
    private final BigDecimal rating;
    private final List<Comment> comments;
    private final List<Ingredient> ingredients;

    private Cocktail(CocktailBuilder builder) {
        super(builder.cocktailId);
        this.name = builder.name;
        this.description = builder.description;
        this.recipe = builder.recipe;
        this.verified = builder.verified;
        this.photo = builder.photo;
        this.author = builder.author;
        this.rating = builder.rating;
        this.comments = builder.comments;
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

    public User getAuthor() {
        return author;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public List<Comment> getComments() {
        return List.copyOf(comments);
    }

    public List<Ingredient> getIngredients() {
        return List.copyOf(ingredients);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cocktail)) return false;
        if (!super.equals(o)) return false;

        Cocktail cocktail = (Cocktail) o;

        if (verified != cocktail.verified) return false;
        if (name != null ? !name.equals(cocktail.name) : cocktail.name != null) return false;
        if (description != null ? !description.equals(cocktail.description) : cocktail.description != null)
            return false;
        if (recipe != null ? !recipe.equals(cocktail.recipe) : cocktail.recipe != null) return false;
        if (photo != null ? !photo.equals(cocktail.photo) : cocktail.photo != null) return false;
        if (author != null ? !author.equals(cocktail.author) : cocktail.author != null) return false;
        if (rating != null ? !rating.equals(cocktail.rating) : cocktail.rating != null) return false;
        if (comments != null ? !comments.equals(cocktail.comments) : cocktail.comments != null) return false;
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
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
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
        sb.append(", author=").append(author);
        sb.append(", rating=").append(rating);
        sb.append(", comments=").append(comments);
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
        private User author;
        private BigDecimal rating;
        private List<Comment> comments;
        private List<Ingredient> ingredients;

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

        public CocktailBuilder author(User author){
            this.author = author;
            return this;
        }

        public CocktailBuilder rating(BigDecimal rating){
            this.rating = rating;
            return this;
        }

        public CocktailBuilder comments(List<Comment> comments){
            this.comments = comments;
            return this;
        }

        public CocktailBuilder ingredients(List<Ingredient> ingredients){
            this.ingredients = ingredients;
            return this;
        }

        public Cocktail build() {
            return new Cocktail(this);
        }
    }
}
