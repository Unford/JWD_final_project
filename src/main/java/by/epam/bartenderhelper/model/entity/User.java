package by.epam.bartenderhelper.model.entity;

import java.util.List;

public final class User extends AbstractDaoEntity {
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private String email;
    private UserRole userRole;
    private Status status;
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
        this.isDeleted = builder.isDeleted;
        this.photo = builder.photo;
        this.reviews = builder.reviews;
        this.cocktails = builder.cocktails;
    }



    public enum Status {
        BANNED,
        WORKING;

        @Override
        public String toString(){
            return super.toString().toLowerCase();
        }
        public static Status defineStatus(String from){
            return Status.valueOf(from.toUpperCase());
        }
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Photo getPhoto() {
        return photo;
    }

    public UserRole getRole() {
        return userRole;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public List<Long> getCocktails() {
        return List.copyOf(cocktails);
    }

    public List<Long> getReviews() {
        return List.copyOf(reviews);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public void setReviews(List<Long> reviews) {
        this.reviews = reviews;
    }

    public void setCocktails(List<Long> cocktails) {
        this.cocktails = cocktails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

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
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
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
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", photo=").append(photo);
        sb.append(", reviews=").append(reviews);
        sb.append(", cocktails=").append(cocktails);
        sb.append('}');
        return sb.toString();
    }

    public static class UserBuilder {
        private long userId;
        private String username;
        private String firstName;
        private String lastName;
        private String description;
        private String email;
        private UserRole userRole;
        private Status status;
        private boolean isDeleted;
        private Photo photo;
        private List<Long> reviews;
        private List<Long> cocktails;

        public UserBuilder userId(long userId){
            this.userId = userId;
            return this;
        }

        public UserBuilder username(String username){
            this.username = username;
            return this;
        }

        public UserBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public UserBuilder description(String description){
            this.description = description;
            return this;
        }

        public UserBuilder email(String email){
            this.email = email;
            return this;
        }

        public UserBuilder reviews(List<Long> reviews){
            this.reviews = reviews;
            return this;
        }

        public UserBuilder role(UserRole userRole){
            this.userRole = userRole;
            return this;
        }

        public UserBuilder status(Status status){
            this.status = status;
            return this;
        }

        public UserBuilder isDeleted(boolean isDeleted){
            this.isDeleted = isDeleted;
            return this;
        }

        public UserBuilder photo(Photo photo){
            this.photo = photo;
            return this;
        }

        public UserBuilder cocktails(List<Long> cocktails){
            this.cocktails = cocktails;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
