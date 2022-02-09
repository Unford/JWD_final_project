package by.epam.bartenderhelper.model.entity;

import java.util.List;

/**
 * The type User.
 */
public final class User extends AbstractDaoEntity {
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private String email;
    private UserRole userRole;
    private Status status;
    private double userRating;
    private boolean isDeleted;
    private Photo photo;
    private List<Long> reviews;

    private List<Long> cocktails;

    private User(UserBuilder builder) {
        super(builder.userId);
        this.username = builder.username;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.description = builder.description;
        this.email = builder.email;
        this.userRole = builder.userRole;
        this.status = builder.status;
        this.userRating = builder.userRating;
        this.isDeleted = builder.isDeleted;
        this.photo = builder.photo;
        this.reviews = builder.reviews;
        this.cocktails = builder.cocktails;
    }


    /**
     * The enum Status.
     */
    public enum Status {
        /**
         * Banned status.
         */
        BANNED,
        /**
         * Working status.
         */
        WORKING;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }

        /**
         * Define status status.
         *
         * @param from the from
         * @return the status
         */
        public static Status defineStatus(String from) {
            return Status.valueOf(from.toUpperCase());
        }
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
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
     * Gets role.
     *
     * @return the role
     */
    public UserRole getRole() {
        return userRole;
    }

    /**
     * Is deleted boolean.
     *
     * @return the boolean
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Gets cocktails.
     *
     * @return the cocktails
     */
    public List<Long> getCocktails() {
        return List.copyOf(cocktails);
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
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Sets deleted.
     *
     * @param deleted the deleted
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     * Sets reviews.
     *
     * @param reviews the reviews
     */
    public void setReviews(List<Long> reviews) {
        this.reviews = reviews;
    }

    /**
     * Sets cocktails.
     *
     * @param cocktails the cocktails
     */
    public void setCocktails(List<Long> cocktails) {
        this.cocktails = cocktails;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (Double.compare(user.userRating, userRating) != 0) return false;
        if (isDeleted != user.isDeleted) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (description != null ? !description.equals(user.description) : user.description != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (userRole != user.userRole) return false;
        if (status != user.status) return false;
        if (photo != null ? !photo.equals(user.photo) : user.photo != null) return false;
        if (reviews != null ? !reviews.equals(user.reviews) : user.reviews != null) return false;
        return cocktails != null ? cocktails.equals(user.cocktails) : user.cocktails == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        temp = Double.doubleToLongBits(userRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isDeleted ? 1 : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (cocktails != null ? cocktails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", userRole=").append(userRole);
        sb.append(", status=").append(status);
        sb.append(", userRating=").append(userRating);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", photo=").append(photo);
        sb.append(", reviews=").append(reviews);
        sb.append(", cocktails=").append(cocktails);
        sb.append('}');
        return sb.toString();
    }

    /**
     * The type User builder.
     */
    public static class UserBuilder {
        private long userId;
        private String username;
        private String firstName;
        private String lastName;
        private String description;
        private String email;
        private UserRole userRole;
        private Status status;
        private double userRating;
        private boolean isDeleted;
        private Photo photo;
        private List<Long> reviews;
        private List<Long> cocktails;

        /**
         * User id user builder.
         *
         * @param userId the user id
         * @return the user builder
         */
        public UserBuilder userId(long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Username user builder.
         *
         * @param username the username
         * @return the user builder
         */
        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * First name user builder.
         *
         * @param firstName the first name
         * @return the user builder
         */
        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Last name user builder.
         *
         * @param lastName the last name
         * @return the user builder
         */
        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Description user builder.
         *
         * @param description the description
         * @return the user builder
         */
        public UserBuilder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Email user builder.
         *
         * @param email the email
         * @return the user builder
         */
        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Reviews user builder.
         *
         * @param reviews the reviews
         * @return the user builder
         */
        public UserBuilder reviews(List<Long> reviews) {
            this.reviews = reviews;
            return this;
        }

        /**
         * Role user builder.
         *
         * @param userRole the user role
         * @return the user builder
         */
        public UserBuilder role(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        /**
         * Status user builder.
         *
         * @param status the status
         * @return the user builder
         */
        public UserBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public UserBuilder userRating(double userRating) {
            this.userRating = userRating;
            return this;
        }

        /**
         * Is deleted user builder.
         *
         * @param isDeleted the is deleted
         * @return the user builder
         */
        public UserBuilder isDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        /**
         * Photo user builder.
         *
         * @param photo the photo
         * @return the user builder
         */
        public UserBuilder photo(Photo photo) {
            this.photo = photo;
            return this;
        }

        /**
         * Cocktails user builder.
         *
         * @param cocktails the cocktails
         * @return the user builder
         */
        public UserBuilder cocktails(List<Long> cocktails) {
            this.cocktails = cocktails;
            return this;
        }

        /**
         * Build user.
         *
         * @return the user
         */
        public User build() {
            return new User(this);
        }
    }
}
