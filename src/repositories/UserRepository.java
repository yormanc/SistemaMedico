package repositories;

import java.util.ArrayList;
import java.util.Optional;

import interfaces.IRepositoryUser;
import models.User;

public class UserRepository implements IRepositoryUser {
    private final ArrayList<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void add(User user) {
        try {
            if (user == null) throw new Exception("El usuario no puede ser nulo");
            if (users.contains(user)) throw new Exception("El usuario ya existe en el repositorio");
            users.add(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar el usuario: " + e.getMessage());
        }
    }

    public void delete(User user) {
        try {
            if (user == null) throw new Exception("El usuario a eliminar no puede ser nulo");
            if (users.isEmpty()) throw new Exception("No hay usuarios registrados");
            boolean removed = users.remove(user);
            if (!removed) throw new Exception("No se encontró el usuario a eliminar");
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    public ArrayList<User> getAll() {
        return new ArrayList<>(users);
    }

    public void update(User updated) {
        try {
            if (updated == null) throw new Exception("El usuario a actualizar no puede ser nulo");
            int index = users.indexOf(updated);
            if (index == -1) throw new Exception("No se encontró el usuario a actualizar");
            users.set(index, updated);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @Override
    public User searchById(int id) {
        try {
            if (id < 0) throw new Exception("El id no es válido");
            Optional<User> found = users.stream()
                .filter(u -> u.getCredentials() != null && u.getCredentials().getId() == id)
                .findFirst();
            return found.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el usuario por id: " + e.getMessage());
        }
    }
}
