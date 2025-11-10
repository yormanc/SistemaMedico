package dto;

import models.Patient;
import models.Credentials;
import enums.UserRole;

/**
 * DTO para serializar/deserializar Pacientes
 * Separa la representación de datos de la lógica de negocio
 */
public class PatientDTO {
    private String fullName;
    private int age;
    private String email;
    private int id;
    private String password;
    private String role;

    // Constructor vacío para Gson
    public PatientDTO() {
    }

    // Constructor completo
    public PatientDTO(String fullName, int age, String email, int id, String password, String role) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.id = id;
        this.password = password;
        this.role = role;
    }

    /**
     * Convierte un Patient a PatientDTO
     */
    public static PatientDTO fromPatient(Patient patient) {
        if (patient == null) {
            return null;
        }
        
        PatientDTO dto = new PatientDTO();
        dto.fullName = patient.getFullName();
        dto.age = patient.getAge();
        dto.email = patient.getEmail();
        dto.id = patient.getCredentials().getId();
        dto.password = patient.getCredentials().getPassword();
        dto.role = patient.getCredentials().getRole().name();
        return dto;
    }

    /**
     * Convierte este DTO a un Patient
     */
    public Patient toPatient() {
        Credentials credentials = new Credentials(id, password, UserRole.valueOf(role));
        return new Patient(fullName, age, email, credentials);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
