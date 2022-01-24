package by.epam.bartenderhelper.model.entity;

import java.util.List;

public final class User extends AbstractDaoEntity {
    private final String login;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Role role;
    private final Status status;
    private final Photo photo;
    private final List<Long> reviews;
    private final List<Long> cocktails;

    private User(UserBuilder builder) {
        super(builder.userId);
        this.login = builder.login;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.role = builder.role;
        this.status = builder.status;
        this.photo = builder.photo;
        this.reviews = builder.reviews;
        this.cocktails = builder.cocktails;
    }

    public enum Role {
        ADMIN,
        CLIENT,
        BARTENDER;

        @Override
        public String toString(){
            return super.toString().toLowerCase();
        }
    }

    public enum Status {
        BANNED,
        WORKING,
        DELETED;

        @Override
        public String toString(){
            return super.toString().toLowerCase();
        }
    }

    public String getLogin() {
        return login;
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

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public Photo getPhoto() {
        return photo;
    }

    public List<Long> getCocktails() {
        return List.copyOf(cocktails);
    }

    public List<Long> getReviews() {
        return List.copyOf(reviews);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;//todo null
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (role != user.role) return false;
        if (status != user.status) return false;
        if (photo != null ? !photo.equals(user.photo) : user.photo != null) return false;
        if (reviews != null ? !reviews.equals(user.reviews) : user.reviews != null) return false;
        return cocktails != null ? cocktails.equals(user.cocktails) : user.cocktails == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (cocktails != null ? cocktails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", role=").append(role);
        sb.append(", status=").append(status);
        sb.append(", photo=").append(photo);
        sb.append(", reviews=").append(reviews);
        sb.append(", cocktails=").append(cocktails);
        sb.append('}');
        return sb.toString();
    }

    public static class UserBuilder {
        private long userId;
        private String login;
        private String firstName;
        private String lastName;
        private String email;
        private Role role;
        private Status status;
        private Photo photo;
        private List<Long> reviews;
        private List<Long> cocktails;

        public UserBuilder userId(long userId){
            this.userId = userId;
            return this;
        }

        public UserBuilder login(String login){
            this.login = login;
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

        public UserBuilder email(String email){
            this.email = email;
            return this;
        }

        public UserBuilder reviews(List<Long> reviews){
            this.reviews = reviews;
            return this;
        }

        public UserBuilder role(Role role){
            this.role = role;
            return this;
        }

        public UserBuilder role(Object role){//todo
            return role(Role.valueOf(role.toString().toUpperCase()));
        }

        public UserBuilder status(Status status){
            this.status = status;
            return this;
        }

        public UserBuilder status(Object status){//todo
            return status(Status.valueOf(status.toString().toUpperCase()));
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
