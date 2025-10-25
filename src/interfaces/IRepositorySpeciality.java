import models.Speciality;
public interface IRepositorySpeciality extends IRepository<Speciality> {
    void add(Speciality speciality);
    void update(Speciality speciality);
    void delete(Speciality specialityToDelete);

}
