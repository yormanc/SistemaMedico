package models;

import java.time.LocalDateTime;

public class Appointment {
    int appointmentId;
    LocalDateTime day;
    LocalDateTime hour;
    Patient patient;
    Doctor doctor;
    Status appointmentStatus;
}
