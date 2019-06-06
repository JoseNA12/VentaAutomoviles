package Model;

public class Employee {

    private String name;
    private Position position;
    private UserType userType;
    //private office                                      ???


    public Employee(String name, Position position, UserType userType) {
        this.name = name;
        this.position = position;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
