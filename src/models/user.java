package models;

public abstract class User {
    private String fullName;
    private int age;
    private String email;
    private Credentials credentials;

    public User() {
    }

    public User(String fullName, int age, String email, Credentials credentials) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.credentials = credentials;
    }

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
}
