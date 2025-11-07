package Services;

import models.Credentials;
import models.User;
import interfaces.IRepositoryUser;

// Clase de prueba para AuthenticationService
public class AuthenticationServiceTest {
    public static void main(String[] args) {
        // Repositorio de usuarios simulado
        java.util.Map<Integer, User> userMap = new java.util.HashMap<>();
        IRepositoryUser repo = new IRepositoryUser() {
            @Override
            public User searchById(int id) {
                return userMap.get(id);
            }
            @Override
            public boolean add(User u) {
                userMap.put(u.getCredentials().getId(), u);
                return true;
            }
            @Override
            public boolean update(User u) {
                userMap.put(u.getCredentials().getId(), u);
                return true;
            }
            @Override
            public boolean remove(User u) {
                userMap.remove(u.getCredentials().getId());
                return true;
            }
        };

    // Puedes añadir usuarios así:
    Credentials cred1 = new Credentials(123, "pass", null);
    User user1 = new User("Juan Perez", 30, "juan@mail.com", cred1) {};
    repo.add(user1);

    Credentials cred2 = new Credentials(456, "clave", null);
    User user2 = new User("Ana Lopez", 28, "ana@mail.com", cred2) {};
    repo.add(user2);

    AuthenticationService auth = new AuthenticationService(repo);

    // Prueba login y logout para ambos usuarios
    User logged1 = auth.login(123, "pass");
    System.out.println("Login Juan: " + (logged1 != null)); // true
    boolean logoutJuan = auth.logout(123);
    System.out.println("Logout Juan: " + logoutJuan); // true

    User logged2 = auth.login(456, "clave");
    System.out.println("Login Ana: " + (logged2 != null)); // true
    boolean logoutAna = auth.logout(456);
    System.out.println("Logout Ana: " + logoutAna); // true

    // Prueba logout con ID inexistente
    boolean logoutFail = auth.logout(999);
    System.out.println("Logout inexistente: " + logoutFail); // false
    }
}
