package interfaces.services;

import models.Doctor;
import models.Speciality;
import java.util.List;

/**
 * Interface para gestión de doctores
 * Principio: Single Responsibility - Solo gestiona doctores
 */
public interface IDoctorService {
    
    /**
     * Registra un nuevo doctor
     */
    boolean registerDoctor(String fullName, int id, String password, 
                          int age, String email, Speciality speciality);
    
    /**
     * Actualiza los datos de un doctor
     */
    boolean updateDoctor(Doctor doctor);
    
    /**
     * Elimina un doctor del sistema
     */
    boolean deleteDoctor(Doctor doctor);
    
    /**
     * Busca un doctor por ID
     */
    Doctor getDoctorById(int doctorId);
    
    /**
     * Obtiene todos los doctores
     */
    List<Doctor> getAllDoctors();
    
    /**
     * Obtiene doctores por especialidad
     */
    List<Doctor> getDoctorsBySpeciality(Speciality speciality);
    
    /**
     * Verifica si existe un doctor con un ID específico
     */
    boolean doctorExists(int doctorId);
}
