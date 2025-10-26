package repositories;

import java.util.ArrayList;
import interfaces.IRepositoryUser;
import models.User;

public class UserRepository implements IRepositoryUser {
    private final ArrayList<User> users; 

    public UserRepository() {
        this.users = new ArrayList<>();  
    }

    @Override
    public boolean add(User user) {
        try {
            // Validaciones básicas
            if (user == null) {
                throw new Exception("El usuario no puede ser nulo");
            }
            if (user.getCredentials() == null) {
                throw new Exception("El usuario debe tener credenciales");
            }

            // Verificar que no exista un usuario con el mismo ID
            boolean userExists = users.stream()
                .anyMatch(u -> u.getCredentials().getId() == user.getCredentials().getId());

            if (userExists) {
                throw new Exception("Ya existe un usuario con este ID");
            }

            // Agregar el usuario
            users.add(user);

        } catch (Exception e) {
            throw new RuntimeException("Error al agregar el usuario: " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        try {
            // Validaciones básicas
            if (user == null) {
                throw new Exception("El usuario a actualizar no puede ser nulo");
            }

            // Buscar el usuario existente
            User existingUser = users.stream()
                .filter(u -> u.getCredentials().getId() == user.getCredentials().getId())
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró el usuario a actualizar"));

            // Actualizar los datos del usuario
            int index = users.indexOf(existingUser);
            users.set(index, user);

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean remove(User userToremove) {
        try {
            if (users.isEmpty()) {
                throw new Exception("No hay usuarios registrados");
            }

            if (userToremove == null) {
                throw new Exception("El usuario a eliminar no puede ser nulo");
            }

            boolean wasRemoved = users.remove(userToremove);
            
            if (!wasRemoved) {
                throw new Exception("No se pudo eliminar el usuario seleccionado");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario: " + e.getMessage());
        }
        return true;
    }

    @Override
    public User searchById(int id) {
        try {
            return users.stream()
                .filter(u -> u.getCredentials().getId() == id)
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró un usuario con el ID: " + id));

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el usuario: " + e.getMessage());
        }
    }

    // Método adicional útil
    public ArrayList<User> getAll() {
        return new ArrayList<>(users);
    }
}
