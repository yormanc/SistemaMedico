package models;

public class Patient {
     private int patientId;
    private String name;
    private String email;
    private String age;

    // Constructor vacío
    public Patient() {
    }

    // Constructor con todos los parámetros
    public Patient(int patientId, String name, String email, String age) {
        this.patientId = patientId;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // Getters y Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
