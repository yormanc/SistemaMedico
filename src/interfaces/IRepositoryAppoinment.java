package interfaces;
import java.util.ArrayList;

import models.Appointment;


public interface IRepositoryAppoinment extends IRepository<Appointment> {
    void add(Appointment appointment);
    void update(Appointment appointment);
    void delete(Appointment appointmentToDelete);
    ArrayList<Appointment> getAll(); 
    ArrayList<Appointment> getByStatus(AppointmentStatus status);
    ArrayList<Appointment> getByPatient(Patient patient);   
    ArrayList<Appointment> getByDoctor(Doctor doctor);
    ArrayList<Appointment> getByDoctorAndStatus(Doctor doctor, AppointmentStatus status);
    ArrayList<Appointment> getByPatientAndStatus(Patient patient, AppointmentStatus status);


}
