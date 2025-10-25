package models;
import enumerations.UserRole;

public class Credentials {
    private int Id;
    private String password;
    private UserRole role;

    // Constructor vacío
    public Credentials() {

    }
    // Constructor con todos los parámetros
    public Credentials(int Id, String password, UserRole role) {
        this.Id = Id;
        this.password = password;
        this.role = role;
    }     
    // Getters y Setters
    public int getId() {
        return Id;
    }  
    public void setId(int Id) {
        this.Id = Id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
} 
