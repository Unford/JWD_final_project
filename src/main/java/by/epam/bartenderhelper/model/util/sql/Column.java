package by.epam.bartenderhelper.model.util.sql;


import java.util.Arrays;

public enum Column {

    //users
    USER_ID("user_id", Type.ID),
    USER_LOGIN("login"),
    USER_PASSWORD("password", Type.NULLABLE),
    USER_FIRST_NAME("first_name"),
    USER_LAST_NAME("last_name"),
    USER_EMAIL("email"),
    USER_ROLE("role"),
    USER_STATUS("status"),
    USER_PHOTO_ID("photo_id"),

    //photos
    PHOTO_ID("photo_id", Type.ID),
    PHOTO_NAME("name"),
    PHOTO_DATA("data"),

    //measures
    MEASURE_ID("measure_id", Type.ID),
    MEASURE_NAME("name"),

    //reviews
    REVIEW_ID("review_id", Type.ID),
    REVIEW_MESSAGE("message"),
    REVIEW_SCORE("score"),
    REVIEW_DATE("date"),
    REVIEW_AUTHOR_ID("author_id"),


    //ingredients
    INGREDIENT_ID("ingredient_id", Type.ID),
    INGREDIENT_NAME("name"),
    INGREDIENT_DESCRIPTION("description"),
    INGREDIENT_STATUS("status"),
    INGREDIENT_PRICE("price"),
    INGREDIENT_CALORIE("calorie"),
    INGREDIENT_PHOTO_ID("photo_id"),
    INGREDIENT_MEASURE_ID("measure_id"),

    //cocktails
    COCKTAIL_ID("cocktail_id", Type.ID),
    COCKTAIL_NAME("name"),
    COCKTAIL_DESCRIPTION("description"),
    COCKTAIL_RECIPE("recipe"),
    COCKTAIL_STATUS("status"),
    COCKTAIL_PHOTO_ID("photo_id"),
    COCKTAIL_AUTHOR_ID("author_id"),

    //usersCocktails
    USERS_COCKTAILS_USER_ID("user_id"),
    USERS_COCKTAILS_COCKTAIL_ID("cocktail_id"),

    //usersReviews
    USERS_REVIEWS_USER_ID("user_id"),
    USERS_REVIEWS_REVIEW_ID("review_id"),

    //cocktailsReviews
    COCKTAILS_REVIEWS_COCKTAIL_ID("cocktail_id"),
    COCKTAILS_REVIEWS_REVIEW_ID("review_id"),

    //cocktailsIngredients
    COCKTAILS_INGREDIENTS_COCKTAIL_ID("cocktail_id"),
    COCKTAILS_INGREDIENTS_INGREDIENT_ID("ingredient_id"),
    COCKTAILS_INGREDIENTS_AMOUNT("amount");

    private final String name;
    private final Type type;

    Column(String name){
        this(name, Type.REQUIRED);
    }

    Column(String name, Type type){
        this.name = name;
        this.type = type;
    }

    public Table getTable(){
        Table table = null;
        for (Table t : Table.values()) {
            boolean res = Arrays.asList(t.getColumns()).contains(this);
            if (res){
                table = t;
                break;
            }
        }
        return table;
    }


    public String getShortName(){
        return formatName(getTable().getShortName());
    }

    public String getFullName(){
        return formatName(getTable().toString());
    }

    private String formatName(String table){
        return "%s.%s".formatted(table, name);
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public enum Type{
        ID,
        REQUIRED,
        NULLABLE
    }
}
