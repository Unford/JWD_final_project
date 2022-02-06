package by.epam.bartenderhelper.model.dao.sql;


import java.util.Arrays;

/**
 * The enum Column.
 */
public enum Column {

    /**
     * User id column.
     */
//users
    USER_ID("user_id", Type.ID),
    /**
     * User username column.
     */
    USER_USERNAME("username"),
    /**
     * User password column.
     */
    USER_PASSWORD("password", Type.NULLABLE),
    /**
     * User first name column.
     */
    USER_FIRST_NAME("first_name"),
    /**
     * User last name column.
     */
    USER_LAST_NAME("last_name"),
    /**
     * User description column.
     */
    USER_DESCRIPTION("description", Type.NULLABLE),
    /**
     * User email column.
     */
    USER_EMAIL("email"),
    /**
     * User role column.
     */
    USER_ROLE("role"),
    /**
     * User status column.
     */
    USER_STATUS("status"),
    /**
     * User is deleted column.
     */
    USER_IS_DELETED("is_deleted"),
    /**
     * User photo id column.
     */
    USER_PHOTO_ID("photo_id", Type.NULLABLE),

    /**
     * Photo id column.
     */
//photos
    PHOTO_ID("photo_id", Type.ID),
    /**
     * Photo name column.
     */
    PHOTO_NAME("name"),
    /**
     * Photo data column.
     */
    PHOTO_DATA("data"),

    /**
     * Measure id column.
     */
//measures
    MEASURE_ID("measure_id", Type.ID),
    /**
     * Measure name column.
     */
    MEASURE_NAME("name"),

    /**
     * Review id column.
     */
//reviews
    REVIEW_ID("review_id", Type.ID),
    /**
     * Review message column.
     */
    REVIEW_MESSAGE("message"),
    /**
     * Review score column.
     */
    REVIEW_SCORE("score"),
    /**
     * Review date column.
     */
    REVIEW_DATE("date"),
    /**
     * Review author id column.
     */
    REVIEW_AUTHOR_ID("author_id"),


    /**
     * Ingredient id column.
     */
//ingredients
    INGREDIENT_ID("ingredient_id", Type.ID),
    /**
     * Ingredient name column.
     */
    INGREDIENT_NAME("name"),
    /**
     * Ingredient description column.
     */
    INGREDIENT_DESCRIPTION("description"),
    /**
     * Ingredient status column.
     */
    INGREDIENT_STATUS("status"),
    /**
     * Ingredient price column.
     */
    INGREDIENT_PRICE("price"),
    /**
     * Ingredient calorie column.
     */
    INGREDIENT_CALORIE("calorie"),
    /**
     * Ingredient photo id column.
     */
    INGREDIENT_PHOTO_ID("photo_id"),
    /**
     * Ingredient measure id column.
     */
    INGREDIENT_MEASURE_ID("measure_id"),

    /**
     * Cocktail id column.
     */
//cocktails
    COCKTAIL_ID("cocktail_id", Type.ID),
    /**
     * Cocktail name column.
     */
    COCKTAIL_NAME("name"),
    /**
     * Cocktail description column.
     */
    COCKTAIL_DESCRIPTION("description"),
    /**
     * Cocktail recipe column.
     */
    COCKTAIL_RECIPE("recipe"),
    /**
     * Cocktail status column.
     */
    COCKTAIL_STATUS("status"),
    /**
     * Cocktail photo id column.
     */
    COCKTAIL_PHOTO_ID("photo_id"),
    /**
     * Cocktail author id column.
     */
    COCKTAIL_AUTHOR_ID("author_id"),

    /**
     * Users cocktails user id column.
     */
//usersCocktails
    USERS_COCKTAILS_USER_ID("user_id"),
    /**
     * Users cocktails cocktail id column.
     */
    USERS_COCKTAILS_COCKTAIL_ID("cocktail_id"),

    /**
     * Users reviews user id column.
     */
//usersReviews
    USERS_REVIEWS_USER_ID("user_id"),
    /**
     * Users reviews review id column.
     */
    USERS_REVIEWS_REVIEW_ID("review_id"),

    /**
     * Cocktails reviews cocktail id column.
     */
//cocktailsReviews
    COCKTAILS_REVIEWS_COCKTAIL_ID("cocktail_id"),
    /**
     * Cocktails reviews review id column.
     */
    COCKTAILS_REVIEWS_REVIEW_ID("review_id"),

    /**
     * Cocktails ingredients cocktail id column.
     */
//cocktailsIngredients
    COCKTAILS_INGREDIENTS_COCKTAIL_ID("cocktail_id"),
    /**
     * Cocktails ingredients ingredient id column.
     */
    COCKTAILS_INGREDIENTS_INGREDIENT_ID("ingredient_id"),
    /**
     * Cocktails ingredients amount column.
     */
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

    /**
     * Get table table.
     *
     * @return the table
     */
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


    /**
     * Get short name string.
     *
     * @return the string
     */
    public String getShortName(){
        return formatName(getTable().getShortName());
    }

    /**
     * Get full name string.
     *
     * @return the string
     */
    public String getFullName(){
        return formatName(getTable().toString());
    }

    private String formatName(String table){
        return "%s.%s".formatted(table, name);
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
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    @Override
    public String toString(){
        return getName();
    }

    /**
     * The enum Type.
     */
    public enum Type{
        /**
         * Id type.
         */
        ID,
        /**
         * Required type.
         */
        REQUIRED,
        /**
         * Nullable type.
         */
        NULLABLE
    }
}
