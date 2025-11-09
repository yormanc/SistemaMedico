package interfaces.repositories;
import models.Doctor;

public interface IRepositoryDoctor extends IRepository<Doctor> {
    boolean add(Doctor doctor);
    boolean update(Doctor doctor);
    boolean remove(Doctor doctor);
    Doctor searchById(int doctorId);

}
