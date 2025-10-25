package models;

// Clase Doctor (Open/Closed, Single Responsibility)
public class Doctor extends User {

    public Doctor() {
    }

    public Doctor(String fullName, int age, String email, Credentials credentials) {
        super(fullName, age, email, credentials);
    }
}
