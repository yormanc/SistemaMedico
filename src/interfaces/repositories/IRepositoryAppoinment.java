package interfaces.repositories;
import java.util.ArrayList;

import enums.*;
import models.Appointment;
import models.Doctor;
import models.Patient; 


public interface IRepositoryAppoinment extends IRepository<Appointment> {
    boolean add(Appointment appointment);
    boolean update(Appointment appointment);
    boolean remove(Appointment appointment);
    ArrayList<Appointment> getAll(); 
    ArrayList<Appointment> getByStatus(AppointmentStatus status);
    ArrayList<Appointment> getByPatient(Patient patient);   
    ArrayList<Appointment> getByDoctor(Doctor doctor);
    ArrayList<Appointment> getByDoctorAndStatus(Doctor doctor,AppointmentStatus status);
    ArrayList<Appointment> getByPatientAndStatus(Patient patient,AppointmentStatus status);


}
