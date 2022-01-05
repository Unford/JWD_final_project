package by.epam.bartenderhelper.dao;

public final class ColumnName {
    private static final String ENTITY_ID = "id";

    //users
    public static final String USER_ID = ENTITY_ID;
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL = "last_name";
    public static final String USER_ROLE = "role";
    public static final String USER_STATUS = "status";
    public static final String USER_PHOTO_ID = "photo_id";

    //photos
    public static final String PHOTO_ID = ENTITY_ID;
    public static final String PHOTO_NAME = "name";
    public static final String PHOTO_DATA = "data";

    //measures
    public static final String MEASURE_ID = ENTITY_ID;
    public static final String MEASURE_NAME = "name";

    //comments
    public static final String COMMENT_ID = ENTITY_ID;
    public static final String COMMENT_MESSAGE = "message";
    public static final String COMMENT_DATE = "date";
    public static final String COMMENT_AUTHOR_ID = "author_id";

    //ratings
    public static final String RATING_ID = ENTITY_ID;
    public static final String RATING_SCORE = "score";
    public static final String RATING_AUTHOR_ID = "author_id";

    //ingredients
    public static final String INGREDIENT_ID = ENTITY_ID;
    public static final String INGREDIENT_NAME = "name";
    public static final String INGREDIENT_DESCRIPTION = "description";
    public static final String INGREDIENT_STATUS = "status";
    public static final String INGREDIENT_PRICE = "price";
    public static final String INGREDIENT_CALORIE = "calorie";
    public static final String INGREDIENT_PHOTO_ID = "photo_id";
    public static final String INGREDIENT_MEASURE_ID = "measure_id";

    //cocktails
    public static final String COCKTAIL_ID = ENTITY_ID;
    public static final String COCKTAIL_NAME = "name";
    public static final String COCKTAIL_DESCRIPTION = "description";
    public static final String COCKTAIL_RECIPE = "recipe";
    public static final String COCKTAIL_STATUS = "status";
    public static final String COCKTAIL_PHOTO_ID = "photo_id";
    public static final String COCKTAIL_AUTHOR_ID = "author_id";

    //usersCocktails
    public static final String USERS_COCKTAILS_USER_ID = "user_id";
    public static final String USERS_COCKTAILS_COCKTAIL_ID = "cocktail_id";

    //usersRatings
    public static final String USERS_RATINGS_USER_ID = "user_id";
    public static final String USERS_RATINGS_RATING_ID = "rating_id";

    //cocktailsRatings
    public static final String COCKTAILS_RATINGS_COCKTAIL_ID = "cocktail_id";
    public static final String COCKTAILS_RATINGS_RATING_ID = "rating_id";

    //cocktailsComments
    public static final String COCKTAILS_COMMENTS_COCKTAIL_ID = "cocktail_id";
    public static final String COCKTAILS_COMMENTS_COMMENT_ID = "comment_id";

    //cocktailsIngredients
    public static final String COCKTAILS_INGREDIENTS_COCKTAIL_ID = "cocktail_id";
    public static final String COCKTAILS_INGREDIENTS_INGREDIENT_ID = "ingredient_id";
    public static final String COCKTAILS_INGREDIENTS_AMOUNT = "amount";

    private ColumnName() {}
}
