package models;
public class Doctor extends User {
    private Speciality speciality;

    // Constructor vacío
    public Doctor() {
    }
    // Constructor con todos los parámetros
    
    public Doctor(String fullName, int age, String email, Credentials credentials,Speciality specialty) {
        super(fullName, age, email, credentials);
        this.speciality = specialty;
        
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
    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpecialty(Speciality speciality) {
        this.speciality = speciality;
    }

  
}