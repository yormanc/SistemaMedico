package services;

import enums.AppointmentStatus;
import enums.UserRole;
import models.*;
import repositories.*;
import services.auth.AuthenticationService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio principal del sistema médico
 * Usa RepositoryManager para garantizar que todos usen los mismos repositorios
 */
public class MedicSystemService {

    private final UserRepository userRepository;
    private final AppoinmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    private final AuthenticationService authService;
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
        
        System.out.println(" MedicSystemService inicializado con repositorios compartidos");
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
     */
    public boolean registerPatient(String fullName, int id, String password, int age, String email) {
        try {
            Credentials credentials = new Credentials(id, password, UserRole.PATIENT);
            Patient patient = new Patient(fullName, age, email, credentials);
            
            boolean added = patientRepository.add(patient);
            
            if (added) {
                System.out.println(" Paciente registrado: " + fullName + " (ID: " + id + ")");
                System.out.println(" Total de pacientes: " + patientRepository.getAll().size());
            } else {
                System.out.println(" No se pudo registrar el paciente: " + fullName);
            }
            
            return added;
            
        } catch (Exception e) {
            System.err.println(" Error al registrar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Registra un nuevo doctor en el sistema
     */
    public boolean registerDoctor(String fullName, int id, String password, int age, String email, Speciality speciality) {
        try {
            Credentials credentials = new Credentials(id, password, UserRole.DOCTOR);
            Doctor doctor = new Doctor(fullName, age, email, credentials, speciality);
            
            boolean added = doctorRepository.add(doctor);
            
            if (added) {
                System.out.println(" Doctor registrado: " + fullName + " (ID: " + id + ")");
            }
            
            return added;
            
        } catch (Exception e) {
            System.err.println(" Error al registrar doctor: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Registra un nuevo administrador
     */
    public boolean registerAdmin(String fullName, int id, String password, int age, String email) {
        try {
            Credentials credentials = new Credentials(id, password, UserRole.ADMIN);
            Patient admin = new Patient(fullName, age, email, credentials);
            return userRepository.add(admin);
        } catch (Exception e) {
            System.err.println(" Error al registrar admin: " + e.getMessage());
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
        return authService.changePassword(userId, oldPass, newPass);
    }
    
    /**
     * Actualiza los datos de un doctor
     */
    public boolean updateDoctor(Doctor doctor) {
        boolean updated = doctorRepository.update(doctor);
        if (updated) {
            System.out.println(" Doctor actualizado: " + doctor.getFullName());
        }
        return updated;
    }
    
    /**
     * Elimina un doctor del sistema
     */
    public boolean removeDoctor(Doctor doctor) {
        boolean removed = doctorRepository.remove(doctor);
        if (removed) {
            System.out.println(" Doctor eliminado: " + doctor.getFullName());
        }
        return removed;
    }
    
    /**
     * Actualiza los datos de un paciente
     */
    public boolean updatePatient(Patient patient) {
        boolean updated = patientRepository.update(patient);
        if (updated) {
            System.out.println(" Paciente actualizado: " + patient.getFullName());
        }
        return updated;
    }
    
    /**
     * Elimina un paciente del sistema
     */
    public boolean removePatient(Patient patient) {
        boolean removed = patientRepository.remove(patient);
        if (removed) {
            System.out.println(" Paciente eliminado: " + patient.getFullName());
            System.out.println(" Total de pacientes: " + patientRepository.getAll().size());
        }
        return removed;
    }
    
    /**
     * Programa una nueva cita médica
     */
    public boolean scheduleAppointment(LocalDateTime dateTime, Patient patient, Doctor doctor, String diagnostic) {
        try {
            Appointment appointment = new Appointment(dateTime, patient, doctor, diagnostic);
            return appointmentRepository.add(appointment);
        } catch (Exception e) {
            System.err.println(" Error al programar cita: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Actualiza el estado de una cita
     */
    public boolean updateAppointment(Appointment appointment,AppointmentStatus status) {
        try {
            appointment.setStatus(status);
            return appointmentRepository.update(appointment);
        } catch (Exception e) {
            System.err.println(" Error al actualizar cita: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Elimina una cita del sistema
     */
    public boolean removeAppointment(Appointment appointment) {
        return appointmentRepository.remove(appointment);
    }
    
    /**
     * Agrega una nueva especialidad
     */
    public boolean addSpeciality(int id, String name, String description) {
        try {
            Speciality speciality = new Speciality(id, name, description);
            return specialityRepository.add(speciality);
        } catch (Exception e) {
            System.err.println(" Error al agregar especialidad: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Actualiza una especialidad existente
     */
    public boolean updateSpeciality(Speciality speciality) {
        return specialityRepository.update(speciality);
    }
    
    /**
     * Elimina una especialidad
     */
    public boolean removeSpeciality(Speciality speciality) {
        return specialityRepository.remove(speciality);
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
    public List<Appointment> viewScheduledAppointmentsDoctor(Doctor doctor, AppointmentStatus status) {
        return appointmentRepository.getByDoctorAndStatus(doctor, status.SCHEDULED);
    }
    
    /**
     * Obtiene el repositorio de pacientes (para uso interno)
     */
    public PatientRepository getPatientRepository() {
        return patientRepository;
    }

    public DoctorRepository getDoctorRepository() {
        return  doctorRepository;
    }

    public AppoinmentRepository getAppointmentRepository() {
        return  appointmentRepository;
    }

    public SpecialityRepository getSpecialityRepository() {
        return specialityRepository;
    }
}