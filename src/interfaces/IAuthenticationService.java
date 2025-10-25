package interfaces;
import models.User;
import models.Credentials;
import models.Patient; 
import models.Doctor;

public interface IAuthenticationService {

    User login(int userId, String password);
    boolean logout(int userId);
    boolean registerDoctor(Doctor doctor, Credentials credentials);
    boolean registerPatient(Patient patient, Credentials credentials);
    boolean registerUser(User user, Credentials credentials);
    boolean changePassword(int userId, String oldPassword, String newPassword);

}
