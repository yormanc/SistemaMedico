package interfaces.repositories;
import models.User;

public interface IRepositoryUser extends IRepository<User> {
    boolean add(User user);
    boolean update(User user);
    boolean remove(User user);
    User searchById(int id);
} 

