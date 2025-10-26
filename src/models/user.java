package models;


// Clase abstracta base para todos los usuarios
public abstract class User implements Authenticable {
    private String fullName;
    private int age;
    private String email;
    private Credentials credentials;

    // Constructor vacío (útil para frameworks)
    public User() {}

    // Constructor con parámetros
    public User(String fullName, int age, String email, Credentials credentials) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.credentials = credentials;
    }

    // Getters y Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    // Implementación de la interfaz Authenticable
    @Override
    public boolean authenticate(String password) {
        return credentials != null && credentials.validatePassword(password);
    }

    // Método abstracto: obliga a las subclases a definir su rol
    public abstract void showRole();
}