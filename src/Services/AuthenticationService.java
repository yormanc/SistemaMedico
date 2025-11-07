package Services;

import interfaces.IAuthenticationService;
import interfaces.IRepositoryUser;
import interfaces.IRepositoryPatient;
import interfaces.IRepositoryDoctor;
import models.User;
import models.Credentials;
import models.Doctor;
import models.Patient;

/**
 * Servicio de autenticación
 * Maneja login, logout, registro y cambio de contraseña
 */
public class AuthenticationService implements IAuthenticationService {

    private IRepositoryUser userRepository;
    private IRepositoryDoctor doctorRepository;
    private IRepositoryPatient patientRepository;
    private User currentUser; // Usuario actualmente autenticado

    /**
     * Constructor que recibe solo el repositorio de usuarios
     * (Para mantener compatibilidad con código existente)
     */
    public AuthenticationService(IRepositoryUser userRepository) {
        this.userRepository = userRepository;
        this.doctorRepository = null;
        this.patientRepository = null;
        this.currentUser = null;
    }

    /**
     * Constructor completo que recibe todos los repositorios
     * (Recomendado para usar con RepositoryManager)
     */
    public AuthenticationService(
        IRepositoryUser userRepository,
        IRepositoryDoctor doctorRepository,
        IRepositoryPatient patientRepository
    ) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.currentUser = null;
    }

    @Override
    public User login(int id, String password) {
        try {
            User user = userRepository.searchById(id);
            if (user != null && user.authenticate(password)) {
                currentUser = user;
                System.out.println("✅ Usuario autenticado: " + user.getFullName());
                return user;
            }
            System.out.println("❌ Credenciales inválidas para ID: " + id);
            return null;
        } catch (Exception e) {
            System.err.println("❌ Error en login: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean logout(int id) {
        try {
            if (currentUser != null && currentUser.getCredentials().getId() == id) {
                System.out.println("✅ Usuario desconectado: " + currentUser.getFullName());
                currentUser = null;
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("❌ Error en logout: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean registerDoctor(Doctor doctor, Credentials credentials) {
        try {
            if (userRepository.searchById(credentials.getId()) == null) {
                userRepository.add(doctor);
                
                // Solo agregar al repositorio de doctores si está disponible
                if (doctorRepository != null) {
                    doctorRepository.add(doctor);
                }
                
                System.out.println("✅ Doctor registrado: " + doctor.getFullName());
                return true;
            }
            System.out.println("❌ Ya existe un usuario con ID: " + credentials.getId());
            return false;
        } catch (Exception e) {
            System.err.println("❌ Error al registrar doctor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean changePassword(int userId, String oldPass, String newPass) {
        try {
            User user = userRepository.searchById(userId);
            if (user != null && user.getCredentials().verifyPassword(oldPass)) {
                user.getCredentials().setPassword(newPass);
                userRepository.update(user);
                System.out.println("✅ Contraseña actualizada para usuario ID: " + userId);
                return true;
            }
            System.out.println("❌ Contraseña actual incorrecta para usuario ID: " + userId);
            return false;
        } catch (Exception e) {
            System.err.println("❌ Error al cambiar contraseña: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean registerPatient(Patient patient, Credentials credentials) {
        try {
            if (userRepository.searchById(credentials.getId()) == null) {
                userRepository.add(patient);
                
                // Solo agregar al repositorio de pacientes si está disponible
                if (patientRepository != null) {
                    patientRepository.add(patient);
                }
                
                System.out.println("✅ Paciente registrado en auth: " + patient.getFullName());
                return true;
            }
            System.out.println("❌ Ya existe un usuario con ID: " + credentials.getId());
            return false;
        } catch (Exception e) {
            System.err.println("❌ Error al registrar paciente en auth: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean registerUser(User user, Credentials credentials) {
        try {
            if (userRepository.searchById(credentials.getId()) == null) {
                userRepository.add(user);
                System.out.println("✅ Usuario registrado: " + user.getFullName());
                return true;
            }
            System.out.println("❌ Ya existe un usuario con ID: " + credentials.getId());
            return false;
        } catch (Exception e) {
            System.err.println("❌ Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene el usuario actualmente autenticado
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Verifica si hay un usuario autenticado
     */
    public boolean isAuthenticated() {
        return currentUser != null;
    }
}