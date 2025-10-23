package models;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private LocalDateTime day;
    private LocalDateTime hour;
    private Patient patient;
    private Doctor doctor;
    private Status appointmentStatus;
    public Appointment(int appointmentId, LocalDateTime day, LocalDateTime hour, Patient patient, Doctor doctor, Status appointmentStatus) {
        this.appointmentId = appointmentId;
        this.day = day;
        this.hour = hour;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentStatus = appointmentStatus;
    }
    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    public LocalDateTime getDay() {
        return day;
    }
    public void setDay(LocalDateTime day) {
        this.day = day;
    }
    public LocalDateTime getHour() {
        return hour;
    }
    public void setHour(LocalDateTime hour) {
        this.hour = hour;
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
    public Status getAppointmentStatus() {
        return appointmentStatus;
    }
    public void setAppointmentStatus(Status appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
    
}
