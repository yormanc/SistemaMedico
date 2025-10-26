package interfaces;
import models.User;

public interface IRepositoryUser extends IRepository<User> {
    boolean add(User user);
    boolean update(User user);
    boolean remove(User userToremove);
    User searchById(int id);}

