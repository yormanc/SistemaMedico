package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int appointmentId;
    private LocalDate date;
    private LocalTime time;
    private Patient patient;
    private Medic medic;
    private int status;
    private String diagnostic;

    // Constructor vacío
    public Appointment() {
    }

    // Constructor con todos los parámetros
    public Appointment(int appointmentId, LocalDate date, LocalTime time, Patient patient, Medic medic, int status, String diagnostic) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.time = time;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
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
