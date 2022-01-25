package by.epam.bartenderhelper.model.entity;

public enum UserRole {
    ADMIN,
    CLIENT,
    BARTENDER,
    GUEST;

    @Override
    public String toString(){
        return super.toString().toLowerCase();
    }

    public static UserRole defineRole(Object from){
       return UserRole.valueOf(from.toString().toUpperCase());
    }
}
