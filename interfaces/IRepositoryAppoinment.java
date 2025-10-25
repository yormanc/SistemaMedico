package interfaces;
import java.util.ArrayList;

import models.Appointment;
import enumerations.AppoinmnetStatus;


public interface IRepositoryAppoinment extends IRepository<Appointment> {
    void add(Appointment appointment);
    void update(Appointment appointment);
    void delete(Appointment appointmentToDelete);
    ArrayList<Appointment> getAll(); 
    ArrayList<Appointment> getByStatus(AppoinmnetStatus status);
    ArrayList<Appointment> getByPatient(Patient patient);   
    ArrayList<Appointment> getByDoctor(Doctor doctor);
    ArrayList<Appointment> getByDoctorAndStatus(Doctor doctor, AppoinmnetStatus status);
    ArrayList<Appointment> getByPatientAndStatus(Patient patient, AppoinmnetStatus status);


}
