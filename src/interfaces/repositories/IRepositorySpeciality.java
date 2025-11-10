package interfaces.repositories;
import models.Speciality;
public interface IRepositorySpeciality extends IRepository<Speciality> {
    boolean add(Speciality speciality);
    boolean update(Speciality speciality);
    boolean remove(Speciality speciality);
    Speciality searchById(int specialityId);
}
