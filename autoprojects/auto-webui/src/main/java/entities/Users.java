package entities;

public class Users implements Cloneable {

    private String username;
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Users() {
        this.username = "";
        this.password = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users getDefaultUser(){
        return new Users("test", "test01");
    }

    @Override
    public String toString() {
        return String.format("%s - %s", username, password);
    }

    @Override
    public Users clone() {
        try {
            return (Users) super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
