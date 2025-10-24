package models;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private LocalDateTime dateTime;
    private Patient patient;
    private Doctor medic;
    private String diagnostic;
    private appoinmentStatus status ;

    // Constructor vacío
    public Appointment() {
    }

    // Constructor con todos los parámetros
    public Appointment(int appointmentId, LocalDateTime dateTime, Patient patient, Doctor medic, int status, String diagnostic) {
        this.appointmentId = appointmentId;
        this.dateTime = dateTime;
        this.patient = patient;
        this.medic = medic;
        this.status = status;
        this.diagnostic = diagnostic;
    }

    // Getters y Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getMedic() {
        return medic;
    }

    public void setMedic(Doctor medic) {
        this.medic = medic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }
}
