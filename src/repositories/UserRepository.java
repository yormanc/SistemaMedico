package repositories;

import java.util.ArrayList;
import java.util.Collections;  // Import necesario para unmodifiableList
import java.util.List;

import models.User;

public class UserRepository {

    private final List<User> users = new ArrayList<>();

    // Agregar un usuario
    public void add(User user) {
        users.add(user);
    }

    // Buscar usuario por email (criterio único)
    public User find(String email) {
        return users.stream()
            .filter(u -> u.getEmail().equalsIgnoreCase(email))
            .findFirst()
            .orElse(null);
    }

    // Listar todos los usuarios
    public List<User> findAll() {
        return Collections.unmodifiableList(users);
    }

    // Eliminar usuario por email
    public void remove(String email) {
        users.removeIf(u -> u.getEmail().equalsIgnoreCase(email));
    }
}