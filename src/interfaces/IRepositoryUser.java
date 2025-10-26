package interfaces;
import models.User;

public interface IRepositoryUser extends IRepository<User> {
    boolean add(User user);
    boolean update(User user);
    boolean remove(User userToDelete);
    User searchById(int id);
    
