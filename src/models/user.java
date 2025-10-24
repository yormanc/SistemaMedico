package models;

// Class for user credentials (Single Responsibility)
public class Credentials {
    private String username;
    private String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getUsername() {
        return username;
    }
}

// Abstract User class (Open/Closed, Liskov Substitution)
public abstract class User implements Authenticable {
    protected String fullName;
    protected int age;
    protected String email;
    protected Credentials credentials;

    public User(String fullName, int age, String email, Credentials credentials) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.credentials = credentials;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    @Override
    public boolean authenticate(String password) {
        return credentials.validatePassword(password);
    }
}

// Concrete implementation (Liskov Substitution)
public class AdminUser extends User {
    public AdminUser(String fullName, int age, String email, Credentials credentials) {
        super(fullName, age, email, credentials);
    }

    public void manageSystem() {
        System.out.println(fullName + " is managing the system.");
    }
}

public class RegularUser extends User {
    public RegularUser(String fullName, int age, String email, Credentials credentials) {
        super(fullName, age, email, credentials);
    }

    public void browseContent() {
        System.out.println(fullName + " is browsing content.");
    }
}