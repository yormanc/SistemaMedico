package interfaces;
import models.Patient;


public interface IRepositoryPatient extends IRepository<Patient> {
    boolean add(Patient patient);
    boolean update(Patient patient);
    boolean remove(Patient patientToremove);
    Patient searchById(int patientId);

}
