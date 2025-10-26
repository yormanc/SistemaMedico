package services;

import interfaces.IAuthenticationService;
import interfaces.IRepositoryUser;
import interfaces.IRepositoryPatient;
import interfaces.IRepositoryDoctor;
import models.User;
import models.Credentials;
import models.Doctor;
import models.Patient;

public class AuthenticationService implements IAuthenticationService {

    private IRepositoryUser userRepository;
    private IRepositoryDoctor doctorRepository;
    private IRepositoryPatient patientRepository;
    private User currentUser; // Usuario actualmente autenticado

    public AuthenticationService(IRepositoryUser userRepository) {
        this.userRepository = userRepository;
            this.currentUser = null;
    }

    @Override
    public User login(int id, String password) {
        User user = userRepository.searchById(id);
        if (user != null && user.authenticate(password)) {
                currentUser = user;
                return user;
        }
        return null;
    }

    @Override
    public boolean logout(int id) {
            if (currentUser != null && currentUser.getCredentials().getId() == id) {
                currentUser = null;
                return true;
            }
            return false;
    }

    @Override
    public boolean registerDoctor(Doctor doctor, Credentials credentials) {
        if (userRepository.searchById(credentials.getId()) == null) {
            userRepository.add(doctor);
            doctorRepository.add(doctor);
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(int userId, String oldPass, String newPass) {
        User user = userRepository.searchById(userId);
        if (user != null && user.getCredentials().verifyPassword(oldPass)) {
            user.getCredentials().setPassword(newPass);
            userRepository.update(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean registerPatient(Patient patient, Credentials credentials) {
        if (userRepository.searchById(credentials.getId()) == null) {
            userRepository.add(patient);
            patientRepository.add(patient);
            return true;
        }
        return false;
    }

    @Override
    public boolean registerUser(User user, Credentials credentials) {
        if (userRepository.searchById(credentials.getId()) == null) {
            userRepository.add(user);
            return true;
        }
        return false;
    }
}