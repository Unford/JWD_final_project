package by.epam.bartenderhelper.entity;

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
