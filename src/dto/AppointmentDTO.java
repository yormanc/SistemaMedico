package dto;

import models.Appointment;
import models.Patient;
import models.Doctor;
import enums.AppointmentStatus;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * DTO para serializar/deserializar Citas
 */
public class AppointmentDTO {
    private int appointmentId;
    private LocalDateTime dateTime;
    private int patientId;
    private int doctorId;
    private String diagnostic;
    private String status;

    // Constructor vacío
    public AppointmentDTO() {
    }

    // Constructor completo
    public AppointmentDTO(int appointmentId, LocalDateTime dateTime, int patientId, 
                          int doctorId, String diagnostic, String status) {
        this.appointmentId = appointmentId;
        this.dateTime = dateTime;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnostic = diagnostic;
        this.status = status;
    }

    /**
     * Convierte un Appointment a AppointmentDTO
     */
    public static AppointmentDTO fromAppointment(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        
        AppointmentDTO dto = new AppointmentDTO();
        dto.appointmentId = appointment.getAppointmentId();
        dto.dateTime = appointment.getDateTime();
        dto.patientId = appointment.getPatient().getCredentials().getId();
        dto.doctorId = appointment.getDoctor().getCredentials().getId();
        dto.diagnostic = appointment.getDiagnostic();
        dto.status = appointment.getStatus().name();
        return dto;
    }

    /**
     * Convierte este DTO a un Appointment
     * Requiere mapas de pacientes y doctores para resolver referencias
     */
    public Appointment toAppointment(Map<Integer, Patient> patientsMap,
                                     Map<Integer, Doctor> doctorsMap) {
        Patient patient = patientsMap.get(patientId);
        Doctor doctor = doctorsMap.get(doctorId);

        if (patient == null) {
            throw new IllegalStateException("Paciente no encontrado con ID: " + patientId);
        }
        if (doctor == null) {
            throw new IllegalStateException("Doctor no encontrado con ID: " + doctorId);
        }

        return new Appointment(
            appointmentId, 
            dateTime, 
            patient, 
            doctor,
            diagnostic, 
            AppointmentStatus.valueOf(status)
        );
    }

    // Getters y Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
