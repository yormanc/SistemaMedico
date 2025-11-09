package interfaces.services;

import models.Appointment;
import models.Patient;
import models.Doctor;
import enums.AppointmentStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface para gestión de citas médicas
 * Principio: Single Responsibility - Solo gestiona citas
 */
public interface IAppointmentService {
    
    /**
     * Programa una nueva cita médica
     */
    boolean scheduleAppointment(LocalDateTime dateTime, Patient patient, 
                                Doctor doctor, String diagnostic);
    
    /**
     * Actualiza una cita existente
     */
    boolean updateAppointment(Appointment appointment);
    
    /**
     * Cambia el estado de una cita
     */
    boolean updateAppointmentStatus(Appointment appointment, AppointmentStatus status);
    
    /**
     * Elimina una cita
     */
    boolean deleteAppointment(Appointment appointment);
    
    /**
     * Busca una cita por ID
     */
    Appointment getAppointmentById(int appointmentId);
    
    /**
     * Obtiene todas las citas
     */
    List<Appointment> getAllAppointments();
    
    /**
     * Obtiene citas por paciente
     */
    List<Appointment> getAppointmentsByPatient(Patient patient);
    
    /**
     * Obtiene citas por doctor
     */
    List<Appointment> getAppointmentsByDoctor(Doctor doctor);
    
    /**
     * Obtiene citas por estado
     */
    List<Appointment> getAppointmentsByStatus(AppointmentStatus status);
    
    /**
     * Obtiene citas de un doctor con un estado específico
     */
    List<Appointment> getAppointmentsByDoctorAndStatus(Doctor doctor, AppointmentStatus status);
    
    /**
     * Obtiene citas de un paciente con un estado específico
     */
    List<Appointment> getAppointmentsByPatientAndStatus(Patient patient, AppointmentStatus status);
}