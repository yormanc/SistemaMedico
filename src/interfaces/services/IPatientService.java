package interfaces.services;

import models.Patient;
import java.util.List;

/**
 * Interface para gestión de pacientes
 * Principio: Single Responsibility - Solo gestiona pacientes
 */
public interface IPatientService {
    
    /**
     * Registra un nuevo paciente
     */
    boolean registerPatient(String fullName, int id, String password, 
                           int age, String email);
    
    /**
     * Actualiza los datos de un paciente
     */
    boolean updatePatient(Patient patient);
    
    /**
     * Elimina un paciente del sistema
     */
    boolean deletePatient(Patient patient);
    
    /**
     * Busca un paciente por ID
     */
    Patient getPatientById(int patientId);
    
    /**
     * Obtiene todos los pacientes
     */
    List<Patient> getAllPatients();
    
    /**
     * Verifica si existe un paciente con un ID específico
     */
    boolean patientExists(int patientId);
}
