package services;

import interfaces.IAuthenticationService;
import interfaces.IRepositoryUser;
import models.User;
import models.Credentials;

public class AuthenticationService implements IAuthenticationService {

    private IRepositoryUser userRepository;

    public AuthenticationService(IRepositoryUser userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(int id, String password) {
        User user = userRepository.searchById(id);
        if (user != null && user.authenticate(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean logout(int id) {
        // En este caso no hay sesión persistente, así que devolvemos true directamente
        return true;
    }

    @Override
    public boolean register(User user, Credentials credentials) {
        if (userRepository.searchById(credentials.getId()) == null) {
            userRepository.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(int userId, String oldPass, String newPass) {
        User user = userRepository.searchById(userId);
        if (user != null && user.getCredentials().validatePassword(oldPass)) {
            user.getCredentials().setPassword(newPass);
            userRepository.modify(user);
            return true;
        }
        return false;
    }
}

