package interfaces;
import models.Doctor;

public interface IRepositoryDoctor extends IRepository<Doctor> {
    boolean add(Doctor doctor);
    boolean update(Doctor doctor);
    boolean delete(Doctor doctorToDelete);
    Doctor searchById(int doctorId);

}
