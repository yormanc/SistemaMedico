package dto;

import models.Doctor;
import models.Credentials;
import models.Speciality;
import enums.UserRole;
import java.util.Map;

/**
 * DTO para serializar/deserializar Doctores
 */
public class DoctorDTO {
    private String fullName;
    private int age;
    private String email;
    private int id;
    private String password;
    private String role;
    private int specialityId;

    // Constructor vacío
    public DoctorDTO() {
    }

    // Constructor completo
    public DoctorDTO(String fullName, int age, String email, int id, String password, 
                     String role, int specialityId) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.id = id;
        this.password = password;
        this.role = role;
        this.specialityId = specialityId;
    }

    /**
     * Convierte un Doctor a DoctorDTO
     */
    public static DoctorDTO fromDoctor(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        
        DoctorDTO dto = new DoctorDTO();
        dto.fullName = doctor.getFullName();
        dto.age = doctor.getAge();
        dto.email = doctor.getEmail();
        dto.id = doctor.getCredentials().getId();
        dto.password = doctor.getCredentials().getPassword();
        dto.role = doctor.getCredentials().getRole().name();
        dto.specialityId = doctor.getSpeciality().getSpecialityId();
        return dto;
    }

    /**
     * Convierte este DTO a un Doctor
     * Requiere un mapa de especialidades para resolver la referencia
     */
    public Doctor toDoctor(Map<Integer, Speciality> specialitiesMap) {
        Credentials credentials = new Credentials(id, password, UserRole.valueOf(role));
        Speciality speciality = specialitiesMap.get(specialityId);
        
        if (speciality == null) {
            throw new IllegalStateException("Especialidad no encontrada con ID: " + specialityId);
        }
        
        return new Doctor(fullName, age, email, credentials, speciality);
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

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }
}
