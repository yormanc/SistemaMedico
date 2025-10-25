package models;

public class Patient extends User {
    public Patient() {
    }

    public Patient(String fullName, int age, String email, Credentials credentials) {
        super(fullName, age, email, credentials);
    }

     public String getFullName() {
        return super.getFullName();
    }
    public void setFullName(String fullName) {
        super.setFullName(fullName);
    }
    public int getAge() {
        return super.getAge();
    }
    public void setAge(int age) {
        super.setAge(age);
    }
    public String getEmail() {
        return super.getEmail();
    }
    public void setEmail(String email) {
        super.setEmail(email);
    }
    public Credentials getCredentials() {
        return super.getCredentials();
    }
    public void setCredentials(Credentials credentials) {
        super.setCredentials(credentials);
    }
}