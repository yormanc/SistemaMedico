package interfaces;
import models.Speciality;
public interface IRepositorySpeciality extends IRepository<Speciality> {
    boolean add(Speciality speciality);
    boolean update(Speciality speciality);
    boolean delete(Speciality specialityToDelete);
    Speciality searchById(int specialityId);
}
