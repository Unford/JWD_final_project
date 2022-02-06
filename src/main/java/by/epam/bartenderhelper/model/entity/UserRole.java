package by.epam.bartenderhelper.model.entity;

/**
 * The enum User role.
 */
public enum UserRole {
    /**
     * Admin user role.
     */
    ADMIN,
    /**
     * Bartender user role.
     */
    BARTENDER,
    /**
     * Client user role.
     */
    CLIENT,
    /**
     * Guest user role.
     */
    GUEST;

    @Override
    public String toString(){
        return super.toString().toLowerCase();
    }

    /**
     * Define role user role.
     *
     * @param from the from
     * @return the user role
     */
    public static UserRole defineRole(String from){
       return UserRole.valueOf(from.toUpperCase());
    }
}
