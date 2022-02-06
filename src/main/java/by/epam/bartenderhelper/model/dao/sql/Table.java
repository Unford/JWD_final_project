package by.epam.bartenderhelper.model.dao.sql;

import java.util.Arrays;
import java.util.stream.Collectors;


import static by.epam.bartenderhelper.model.dao.sql.Column.*;

/**
 * The enum Table.
 */
public enum Table {
    /**
     * Users table.
     */
    USERS (USER_ID, USER_USERNAME, USER_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_DESCRIPTION,
            USER_EMAIL, USER_ROLE, USER_STATUS, USER_IS_DELETED, USER_PHOTO_ID),

    /**
     * Photos table.
     */
    PHOTOS (PHOTO_ID, PHOTO_NAME, PHOTO_DATA),

    /**
     * Ingredients table.
     */
    INGREDIENTS (INGREDIENT_ID, INGREDIENT_NAME, INGREDIENT_DESCRIPTION,
            INGREDIENT_STATUS, INGREDIENT_PRICE, INGREDIENT_CALORIE,
            INGREDIENT_PHOTO_ID, INGREDIENT_MEASURE_ID),

    /**
     * Cocktails table.
     */
    COCKTAILS (COCKTAIL_ID, COCKTAIL_NAME, COCKTAIL_DESCRIPTION, COCKTAIL_RECIPE,
            COCKTAIL_STATUS, COCKTAIL_PHOTO_ID, COCKTAIL_AUTHOR_ID),

    /**
     * Reviews table.
     */
    REVIEWS (REVIEW_ID, REVIEW_MESSAGE, REVIEW_SCORE, REVIEW_DATE, REVIEW_AUTHOR_ID),

    /**
     * Measures table.
     */
    MEASURES (MEASURE_ID, MEASURE_NAME),

    /**
     * Users reviews table.
     */
    USERS_REVIEWS (USERS_REVIEWS_USER_ID, USERS_REVIEWS_REVIEW_ID),

    /**
     * Users cocktails table.
     */
    USERS_COCKTAILS (USERS_COCKTAILS_USER_ID, USERS_COCKTAILS_COCKTAIL_ID),

    /**
     * Cocktails reviews table.
     */
    COCKTAILS_REVIEWS (COCKTAILS_REVIEWS_COCKTAIL_ID, COCKTAILS_REVIEWS_REVIEW_ID),

    /**
     * Cocktails ingredients table.
     */
    COCKTAILS_INGREDIENTS (COCKTAILS_INGREDIENTS_COCKTAIL_ID,
            COCKTAILS_INGREDIENTS_INGREDIENT_ID,
            COCKTAILS_INGREDIENTS_AMOUNT);


    private final Column[] columns;

    Table(Column... columns){
        this.columns = columns;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    private static final String FIRST_LETTER_DELIMITER = "^|_";

    /**
     * Gets short name.
     *
     * @return the short name
     */
    public String getShortName() {
        return Arrays.stream(this.toString().split(FIRST_LETTER_DELIMITER))
                .map(s -> Character.toString(s.charAt(0)))
                .collect(Collectors.joining());
    }

    /**
     * Get id column column.
     *
     * @return the column
     */
    public Column getIdColumn(){
        return Arrays.stream(columns)
                .filter(column -> column.getType() == Type.ID)
                .findFirst()
                .orElse(null);
    }

    /**
     * Get columns column [ ].
     *
     * @return the column [ ]
     */
    public Column[] getColumns(){
        return columns;
    }
}
