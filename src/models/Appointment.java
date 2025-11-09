package models;
import java.time.LocalDateTime;

import enums.AppoinmnetStatus;

public class Appointment {

    private static int nextId = 1; // Variable estática para auto-incremento
    
    private int appointmentId;
    private LocalDateTime dateTime;
    private Patient patient;
    private Doctor doctor;
    private String diagnostic;
    private AppoinmnetStatus status;

    // Constructor vacío - genera ID automáticamente
    public Appointment() {
        this.appointmentId = nextId++;
    }

    // Constructor sin ID - genera ID automáticamente
    public Appointment(LocalDateTime dateTime, Patient patient, Doctor doctor, String diagnostic) {
        this.appointmentId = nextId++;
        this.dateTime = dateTime;
        this.patient = patient;
        this.doctor = doctor;
        this.status = AppoinmnetStatus.SCHEDULED;
        this.diagnostic = diagnostic;
    }

    // Constructor con todos los parámetros (para cargar datos existentes)
    public Appointment(int appointmentId, LocalDateTime dateTime, Patient patient, Doctor doctor, String diagnostic, AppoinmnetStatus status) {
        this.appointmentId = appointmentId;
        this.dateTime = dateTime;
        this.patient = patient;
        this.doctor = doctor;
        this.status = status;
        this.diagnostic = diagnostic;
        
        if (appointmentId >= nextId) {
            nextId = appointmentId + 1;
        }
    }

    // Getters y Setters
    public int getAppointmentId() {
        return appointmentId;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public AppoinmnetStatus getStatus() {
        return status;
    }

    public void setStatus(AppoinmnetStatus status) {
        this.status = status;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    // Método estático para obtener el próximo ID (útil para debugging)
    public static int getNextId() {
        return nextId;
    }

    // Método estático para resetear el contador (útil para testing)
    public static void resetIdCounter() {
        nextId = 1;
    }
}

