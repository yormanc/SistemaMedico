package interfaces;
import models.User;
import models.Credentials; 

public interface IAuthenticationService {

    User login(int userId, String password);
    boolean logout(int userId);
    boolean register(User user, Credentials credentials);
    boolean changePassword(int userId, String oldPassword, String newPassword);

}