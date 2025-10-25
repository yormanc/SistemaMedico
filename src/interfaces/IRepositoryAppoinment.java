package interfaces;
import java.util.ArrayList;
import models.Appointment;
import enumerations.*;
import models.Doctor;
import models.Patient; 


public interface IRepositoryAppoinment extends IRepository<Appointment> {
    boolean add(Appointment appointment);
    boolean update(Appointment appointment);
    boolean delete(Appointment appointmentToDelete);
    ArrayList<Appointment> getAll(); 
    ArrayList<Appointment> getByStatus(AppoinmnetStatus status);
    ArrayList<Appointment> getByPatient(Patient patient);   
    ArrayList<Appointment> getByDoctor(Doctor doctor);
    ArrayList<Appointment> getByDoctorAndStatus(Doctor doctor,AppoinmnetStatus status);
    ArrayList<Appointment> getByPatientAndStatus(Patient patient,AppoinmnetStatus status);


}
