package services;

import enumerations.AppoinmnetStatus;
import enumerations.UserRole;
import models.*;
import repositories.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio principal del sistema médico
 * VERSIÓN CORREGIDA: Ahora registra pacientes y doctores también en UserRepository
 */
public class MedicSystemService {

    public final UserRepository userRepository;
    public final AppoinmentRepository appointmentRepository;
    public final PatientRepository patientRepository;
    public final DoctorRepository doctorRepository;
    public final SpecialityRepository specialityRepository;
    public final AuthenticationService authService;
    public User authenticatedUser;

    /**
     * Constructor por defecto - USA REPOSITORYMANAGER
     */
    public MedicSystemService() {
        // Obtener la instancia única del gestor de repositorios
        RepositoryManager repoManager = RepositoryManager.getInstance();

        // Usar las instancias compartidas de los repositorios
        this.userRepository = repoManager.getUserRepository();
        this.appointmentRepository = repoManager.getAppointmentRepository();
        this.patientRepository = repoManager.getPatientRepository();
        this.doctorRepository = repoManager.getDoctorRepository();
        this.specialityRepository = repoManager.getSpecialityRepository();

        // Inicializar AuthenticationService con todos los repositorios
        this.authService = new AuthenticationService(
                userRepository,
                doctorRepository,
                patientRepository
        );
        this.authenticatedUser = null;

        System.out.println("✅ MedicSystemService inicializado con repositorios compartidos");
    }

    /**
     * Constructor con inyección de dependencias (para testing)
     */
    public MedicSystemService(
            UserRepository userRepository,
            AppoinmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            SpecialityRepository specialityRepository,
            AuthenticationService authService,
            User authenticatedUser
    ) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.authService = authService;
        this.authenticatedUser = null;
    }

    /**
     * Registra un nuevo paciente en el sistema
     * ✅ CORREGIDO: Ahora también lo agrega a UserRepository
     */
    public boolean registerPatient(String fullName, int id, String password, int age, String email) {
        try {
            Credentials credentials = new Credentials(id, password, UserRole.PATIENT);
            Patient patient = new Patient(fullName, age, email, credentials);

            // ✅ Agregar a PatientRepository
            boolean addedToPatientRepo = patientRepository.add(patient);

            if (addedToPatientRepo) {
                // ✅ IMPORTANTE: También agregar a UserRepository para que funcione el login
                boolean addedToUserRepo = userRepository.add(patient);

                if (addedToUserRepo) {
                    System.out.println("✅ Paciente registrado: " + fullName + " (ID: " + id + ")");
                    System.out.println("📊 Total de pacientes: " + patientRepository.getAll().size());
                    System.out.println("👥 Total de usuarios: " + userRepository.getAll().size());

                    // Guardar cambios en disco
                    RepositoryManager.getInstance().forceSave();

                    return true;
                } else {
                    // Si falla agregar a UserRepository, remover de PatientRepository
                    patientRepository.remove(patient);
                    System.out.println("❌ Error: El ID ya existe en el sistema de usuarios");
                    return false;
                }
            } else {
                System.out.println("❌ No se pudo registrar el paciente: " + fullName);
                return false;
            }

        } catch (Exception e) {
            System.err.println("❌ Error al registrar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Registra un nuevo doctor en el sistema
     * ✅ CORREGIDO: Ahora también lo agrega a UserRepository
     */
    public boolean registerDoctor(String fullName, int id, String password, int age, String email, Speciality speciality) {
        try {
            Credentials credentials = new Credentials(id, password, UserRole.DOCTOR);
            Doctor doctor = new Doctor(fullName, age, email, credentials, speciality);

            // ✅ Agregar a DoctorRepository
            boolean addedToDoctorRepo = doctorRepository.add(doctor);

            if (addedToDoctorRepo) {
                // ✅ IMPORTANTE: También agregar a UserRepository para que funcione el login
                boolean addedToUserRepo = userRepository.add(doctor);

                if (addedToUserRepo) {
                    System.out.println("✅ Doctor registrado: " + fullName + " (ID: " + id + ")");
                    System.out.println("⚕️  Total de doctores: " + doctorRepository.getAll().size());
                    System.out.println("👥 Total de usuarios: " + userRepository.getAll().size());

                    // Guardar cambios en disco
                    RepositoryManager.getInstance().forceSave();

                    return true;
                } else {
                    // Si falla agregar a UserRepository, remover de DoctorRepository
                    doctorRepository.remove(doctor);
                    System.out.println("❌ Error: El ID ya existe en el sistema de usuarios");
                    return false;
                }
            } else {
                System.out.println("❌ No se pudo registrar el doctor: " + fullName);
                return false;
            }

        } catch (Exception e) {
            System.err.println("❌ Error al registrar doctor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Registra un nuevo administrador
     * ✅ Ya estaba correcto, pero agregamos guardado automático
     */
    public boolean registerAdmin(String fullName, int id, String password, int age, String email) {
        try {
            Credentials credentials = new Credentials(id, password, UserRole.ADMIN);
            Patient admin = new Patient(fullName, age, email, credentials);
            boolean added = userRepository.add(admin);

            if (added) {
                System.out.println("✅ Administrador registrado: " + fullName + " (ID: " + id + ")");
                // Guardar cambios en disco
                RepositoryManager.getInstance().forceSave();
            }

            return added;
        } catch (Exception e) {
            System.err.println("❌ Error al registrar admin: " + e.getMessage());
            return false;
        }
    }

    /**
     * Inicia sesión de un usuario
     */
    public void loginUser(int id, String password) {
        authenticatedUser = authService.login(id, password);
    }

    /**
     * Cierra sesión del usuario actual
     */
    public boolean logoutUser(int id) {
        boolean result = authService.logout(id);
        if (result) {
            authenticatedUser = null;
        }
        return result;
    }

    /**
     * Cambia la contraseña de un usuario
     */
    public boolean changeUserPassword(int userId, String oldPass, String newPass) {
        boolean changed = authService.changePassword(userId, oldPass, newPass);
        if (changed) {
            // Guardar cambios en disco
            RepositoryManager.getInstance().forceSave();
        }
        return changed;
    }

    /**
     * Actualiza los datos de un doctor
     */
    public boolean updateDoctor(Doctor doctor) {
        boolean updated = doctorRepository.update(doctor);
        if (updated) {
            // También actualizar en UserRepository
            userRepository.update(doctor);
            System.out.println("✅ Doctor actualizado: " + doctor.getFullName());
            // Guardar cambios en disco
            RepositoryManager.getInstance().forceSave();
        }
        return updated;
    }

    /**
     * Elimina un doctor del sistema
     */
    public boolean removeDoctor(Doctor doctor) {
        boolean removed = doctorRepository.remove(doctor);
        if (removed) {
            // También remover de UserRepository
            userRepository.remove(doctor);
            System.out.println("✅ Doctor eliminado: " + doctor.getFullName());
            // Guardar cambios en disco
            RepositoryManager.getInstance().forceSave();
        }
        return removed;
    }

    /**
     * Actualiza los datos de un paciente
     */
    public boolean updatePatient(Patient patient) {
        boolean updated = patientRepository.update(patient);
        if (updated) {
            // También actualizar en UserRepository
            userRepository.update(patient);
            System.out.println("✅ Paciente actualizado: " + patient.getFullName());
            // Guardar cambios en disco
            RepositoryManager.getInstance().forceSave();
        }
        return updated;
    }

    /**
     * Elimina un paciente del sistema
     */
    public boolean removePatient(Patient patient) {
        boolean removed = patientRepository.remove(patient);
        if (removed) {
            // También remover de UserRepository
            userRepository.remove(patient);
            System.out.println("✅ Paciente eliminado: " + patient.getFullName());
            System.out.println("📊 Total de pacientes: " + patientRepository.getAll().size());
            // Guardar cambios en disco
            RepositoryManager.getInstance().forceSave();
        }
        return removed;
    }

    /**
     * Programa una nueva cita médica
     */
    public boolean scheduleAppointment(LocalDateTime dateTime, Patient patient, Doctor doctor, String diagnostic) {
        try {
            Appointment appointment = new Appointment(dateTime, patient, doctor, diagnostic);
            boolean added = appointmentRepository.add(appointment);
            if (added) {
                // Guardar cambios en disco
                RepositoryManager.getInstance().forceSave();
            }
            return added;
        } catch (Exception e) {
            System.err.println("❌ Error al programar cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza el estado de una cita
     */
    public boolean updateAppointment(Appointment appointment, AppoinmnetStatus status) {
        try {
            appointment.setStatus(status);
            boolean updated = appointmentRepository.update(appointment);
            if (updated) {
                // Guardar cambios en disco
                RepositoryManager.getInstance().forceSave();
            }
            return updated;
        } catch (Exception e) {
            System.err.println("❌ Error al actualizar cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una cita del sistema
     */
    public boolean removeAppointment(Appointment appointment) {
        boolean removed = appointmentRepository.remove(appointment);
        if (removed) {
            // Guardar cambios en disco
            RepositoryManager.getInstance().forceSave();
        }
        return removed;
    }

    /**
     * Agrega una nueva especialidad
     */
    public boolean addSpeciality(int id, String name, String description) {
        try {
            Speciality speciality = new Speciality(id, name, description);
            boolean added = specialityRepository.add(speciality);
            if (added) {
                // Guardar cambios en disco
                RepositoryManager.getInstance().forceSave();
            }
            return added;
        } catch (Exception e) {
            System.err.println("❌ Error al agregar especialidad: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza una especialidad existente
     */
    public boolean updateSpeciality(Speciality speciality) {
        boolean updated = specialityRepository.update(speciality);
        if (updated) {
            // Guardar cambios en disco
            RepositoryManager.getInstance().forceSave();
        }
        return updated;
    }

    /**
     * Elimina una especialidad
     */
    public boolean removeSpeciality(Speciality speciality) {
        boolean removed = specialityRepository.remove(speciality);
        if (removed) {
            // Guardar cambios en disco
            RepositoryManager.getInstance().forceSave();
        }
        return removed;
    }

    /**
     * Obtiene el historial de citas de un paciente
     */
    public List<Appointment> viewPatientHistory(Patient patient) {
        return appointmentRepository.getByPatient(patient);
    }

    /**
     * Obtiene el historial de citas de un doctor
     */
    public List<Appointment> viewDoctorHistory(Doctor doctor) {
        return appointmentRepository.getByDoctor(doctor);
    }

    /**
     * Obtiene las citas programadas de un doctor
     */
    public List<Appointment> viewScheduledAppointmentsDoctor(Doctor doctor, AppoinmnetStatus status) {
        return appointmentRepository.getByDoctorAndStatus(doctor, AppoinmnetStatus.SCHEDULED);
    }

    /**
     * Obtiene el repositorio de pacientes (para uso interno)
     */
    public PatientRepository getPatientRepository() {
        return patientRepository;
    }

    public DoctorRepository getDoctorRepository() {
        return doctorRepository;
    }

    public SpecialityRepository getSpecialityRepository() {
        return specialityRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;}

    public AppoinmentRepository getAppointmentRepository() {
        return appointmentRepository;
    }
}
