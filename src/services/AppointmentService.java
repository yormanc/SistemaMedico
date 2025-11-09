package services;

import interfaces.services.IAppointmentService;
import interfaces.repositories.IRepositoryAppoinment;
import models.Appointment;
import models.Patient;
import models.Doctor;
import enums.AppointmentStatus;
import repositories.RepositoryManager;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio para gestión de citas médicas
 * Responsabilidad: Solo operaciones CRUD de citas
 */
public class AppointmentService implements IAppointmentService {
    
    private final IRepositoryAppoinment appointmentRepository;
    
    public AppointmentService(IRepositoryAppoinment appointmentRepository) {
        if (appointmentRepository == null) {
            throw new IllegalArgumentException("El repositorio de citas no puede ser nulo");
        }
        this.appointmentRepository = appointmentRepository;
    }
    
    @Override
    public boolean scheduleAppointment(LocalDateTime dateTime, Patient patient, 
                                      Doctor doctor, String diagnostic) {
        try {
            if (dateTime == null || patient == null || doctor == null) {
                System.err.println("Datos de cita inválidos");
                return false;
            }
            
            Appointment appointment = new Appointment(dateTime, patient, doctor, diagnostic);
            boolean added = appointmentRepository.add(appointment);
            
            if (added) {
                RepositoryManager.getInstance().forceSave();
                System.out.println("Cita programada exitosamente");
            }
            
            return added;
            
        } catch (Exception e) {
            System.err.println("Error al programar cita: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean updateAppointment(Appointment appointment) {
        try {
            if (appointment == null) {
                System.err.println("La cita no puede ser nula");
                return false;
            }
            
            boolean updated = appointmentRepository.update(appointment);
            
            if (updated) {
                RepositoryManager.getInstance().forceSave();
                System.out.println("Cita actualizada exitosamente");
            }
            
            return updated;
            
        } catch (Exception e) {
            System.err.println("Error al actualizar cita: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean updateAppointmentStatus(Appointment appointment, AppointmentStatus status) {
        try {
            if (appointment == null || status == null) {
                System.err.println("Datos inválidos para actualizar estado");
                return false;
            }
            
            appointment.setStatus(status);
            return updateAppointment(appointment);
            
        } catch (Exception e) {
            System.err.println("Error al actualizar estado de cita: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean deleteAppointment(Appointment appointment) {
        try {
            if (appointment == null) {
                System.err.println("La cita no puede ser nula");
                return false;
            }
            
            boolean removed = appointmentRepository.remove(appointment);
            
            if (removed) {
                RepositoryManager.getInstance().forceSave();
                System.out.println("Cita eliminada exitosamente");
            }
            
            return removed;
            
        } catch (Exception e) {
            System.err.println("Error al eliminar cita: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Appointment getAppointmentById(int appointmentId) {
        try {
            return appointmentRepository.searchById(appointmentId);
        } catch (Exception e) {
            System.err.println("Error al buscar cita: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Appointment> getAllAppointments() {
        try {
            return appointmentRepository.getAll();
        } catch (Exception e) {
            System.err.println("Error al obtener citas: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Appointment> getAppointmentsByPatient(Patient patient) {
        try {
            if (patient == null) {
                System.err.println("El paciente no puede ser nulo");
                return List.of();
            }
            return appointmentRepository.getByPatient(patient);
        } catch (Exception e) {
            System.err.println("Error al obtener citas del paciente: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
        try {
            if (doctor == null) {
                System.err.println("El doctor no puede ser nulo");
                return List.of();
            }
            return appointmentRepository.getByDoctor(doctor);
        } catch (Exception e) {
            System.err.println("Error al obtener citas del doctor: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Appointment> getAppointmentsByStatus(AppointmentStatus status) {
        try {
            if (status == null) {
                System.err.println("El estado no puede ser nulo");
                return List.of();
            }
            return appointmentRepository.getByStatus(status);
        } catch (Exception e) {
            System.err.println("Error al obtener citas por estado: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Appointment> getAppointmentsByDoctorAndStatus(Doctor doctor, 
                                                              AppointmentStatus status) {
        try {
            if (doctor == null || status == null) {
                System.err.println("Los parámetros no pueden ser nulos");
                return List.of();
            }
            return appointmentRepository.getByDoctorAndStatus(doctor, status);
        } catch (Exception e) {
            System.err.println("Error al obtener citas: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Appointment> getAppointmentsByPatientAndStatus(Patient patient, 
                                                               AppointmentStatus status) {
        try {
            if (patient == null || status == null) {
                System.err.println("Los parámetros no pueden ser nulos");
                return List.of();
            }
            return appointmentRepository.getByPatientAndStatus(patient, status);
        } catch (Exception e) {
            System.err.println("Error al obtener citas: " + e.getMessage());
            return List.of();
        }
    }
}