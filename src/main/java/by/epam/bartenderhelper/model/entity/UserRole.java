package by.epam.bartenderhelper.model.entity;

public enum UserRole {
    ADMIN,
    BARTENDER,
    CLIENT,
    GUEST;

    @Override
    public String toString(){
        return super.toString().toLowerCase();
    }

    public static UserRole defineRole(String from){
       return UserRole.valueOf(from.toUpperCase());
    }
}
