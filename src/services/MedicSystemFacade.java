package services;

import interfaces.services.*;
import models.*;
import enums.*;
import repositories.RepositoryManager;
import services.auth.AuthenticationService;
import java.time.LocalDateTime;
import java.util.List;

/**
 * FACADE PATTERN - Punto de entrada único al sistema
 * Reemplaza a MedicSystemService
 * 
 * Responsabilidad: Coordinar todos los servicios del sistema
 * NO tiene lógica de negocio, solo delega a los servicios especializados
 */
public class MedicSystemFacade {
    
    // Servicios especializados
    private final AuthenticationService authenticationService;
    private final IAppointmentService appointmentService;
    private final ISpecialityService specialityService;
    private final IDoctorService doctorService;
    private final IPatientService patientService;
    
    /**
     * Constructor - Inicializa todos los servicios
     */
    public MedicSystemFacade() {
        // Obtener repositorios del gestor centralizado
        RepositoryManager repoManager = RepositoryManager.getInstance();
        
        // Inicializar servicios
        this.authenticationService = new AuthenticationService(
            repoManager.getUserRepository(),
            repoManager.getDoctorRepository(),
            repoManager.getPatientRepository()
        );
        
        this.appointmentService = new AppointmentService(
            repoManager.getAppointmentRepository()
        );
        
        this.specialityService = new SpecialityService(
            repoManager.getSpecialityRepository()
        );
        
        this.doctorService = new DoctorService(
            repoManager.getDoctorRepository(),
            repoManager.getUserRepository()
        );
        
        this.patientService = new PatientService(
            repoManager.getPatientRepository(),
            repoManager.getUserRepository()
        );
        
        System.out.println("MedicSystemFacade inicializado - Todos los servicios listos");
    }
    
    // ==================== AUTENTICACIÓN ====================
    
    public User login(int id, String password) {
        return authenticationService.login(id, password);
    }
    
    public boolean logout(int id) {
        return authenticationService.logout(id);
    }
    
    public User getCurrentUser() {
        return authenticationService.getCurrentUser();
    }
    
    public boolean isAuthenticated() {
        return authenticationService.isAuthenticated();
    }
    
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        return authenticationService.changePassword(userId, oldPassword, newPassword);
    }
    
    // ==================== GESTIÓN DE PACIENTES ====================
    
    public boolean registerPatient(String fullName, int id, String password, 
                                  int age, String email) {
        return patientService.registerPatient(fullName, id, password, age, email);
    }
    
    public boolean updatePatient(Patient patient) {
        return patientService.updatePatient(patient);
    }
    
    public boolean deletePatient(Patient patient) {
        return patientService.deletePatient(patient);
    }
    
    public Patient getPatientById(int patientId) {
        return patientService.getPatientById(patientId);
    }
    
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
    
    // ==================== GESTIÓN DE DOCTORES ====================
    
    public boolean registerDoctor(String fullName, int id, String password, 
                                 int age, String email, Speciality speciality) {
        return doctorService.registerDoctor(fullName, id, password, age, email, speciality);
    }
    
    public boolean updateDoctor(Doctor doctor) {
        return doctorService.updateDoctor(doctor);
    }
    
    public boolean deleteDoctor(Doctor doctor) {
        return doctorService.deleteDoctor(doctor);
    }
    
    public Doctor getDoctorById(int doctorId) {
        return doctorService.getDoctorById(doctorId);
    }
    
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
    
    public List<Doctor> getDoctorsBySpeciality(Speciality speciality) {
        return doctorService.getDoctorsBySpeciality(speciality);
    }
    
    // ==================== GESTIÓN DE ESPECIALIDADES ====================
    
    public boolean addSpeciality(int id, String name, String description) {
        return specialityService.addSpeciality(id, name, description);
    }
    
    public boolean updateSpeciality(Speciality speciality) {
        return specialityService.updateSpeciality(speciality);
    }
    
    public boolean deleteSpeciality(Speciality speciality) {
        return specialityService.deleteSpeciality(speciality);
    }
    
    public Speciality getSpecialityById(int specialityId) {
        return specialityService.getSpecialityById(specialityId);
    }
    
    public Speciality getSpecialityByName(String name) {
        return specialityService.getSpecialityByName(name);
    }
    
    public List<Speciality> getAllSpecialities() {
        return specialityService.getAllSpecialities();
    }
    
    // ==================== GESTIÓN DE CITAS ====================
    
    public boolean scheduleAppointment(LocalDateTime dateTime, Patient patient, 
                                      Doctor doctor, String diagnostic) {
        return appointmentService.scheduleAppointment(dateTime, patient, doctor, diagnostic);
    }
    
    public boolean updateAppointment(Appointment appointment) {
        return appointmentService.updateAppointment(appointment);
    }
    
    public boolean updateAppointmentStatus(Appointment appointment, AppointmentStatus status) {
        return appointmentService.updateAppointmentStatus(appointment, status);
    }
    
    public boolean deleteAppointment(Appointment appointment) {
        return appointmentService.deleteAppointment(appointment);
    }
    
    public Appointment getAppointmentById(int appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }
    
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    
    public List<Appointment> getAppointmentsByPatient(Patient patient) {
        return appointmentService.getAppointmentsByPatient(patient);
    }
    
    public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
        return appointmentService.getAppointmentsByDoctor(doctor);
    }
    
    public List<Appointment> getAppointmentsByDoctorAndStatus(Doctor doctor, 
                                                              AppointmentStatus status) {
        return appointmentService.getAppointmentsByDoctorAndStatus(doctor, status);
    }
    
    public List<Appointment> getAppointmentsByPatientAndStatus(Patient patient, 
                                                               AppointmentStatus status) {
        return appointmentService.getAppointmentsByPatientAndStatus(patient, status);
    }
    
    // ==================== ACCESO A SERVICIOS (Para uso avanzado) ====================
    
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
    
    public IAppointmentService getAppointmentService() {
        return appointmentService;
    }
    
    public ISpecialityService getSpecialityService() {
        return specialityService;
    }
    
    public IDoctorService getDoctorService() {
        return doctorService;
    }
    
    public IPatientService getPatientService() {
        return patientService;
    }
    
    // ==================== UTILIDADES ====================
    
    /**
     * Imprime estadísticas del sistema
     */
    public void printSystemStats() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("       ESTADÍSTICAS DEL SISTEMA");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Pacientes:      " + getAllPatients().size());
        System.out.println("Doctores:       " + getAllDoctors().size());
        System.out.println("Especialidades: " + getAllSpecialities().size());
        System.out.println("Citas:          " + getAllAppointments().size());
        System.out.println("═══════════════════════════════════════════\n");
    }
    
    /**
     * Guarda todos los datos inmediatamente
     */
    public void saveData() {
        RepositoryManager.getInstance().forceSave();
    }
}