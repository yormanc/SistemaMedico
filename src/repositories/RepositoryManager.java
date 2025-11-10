package repositories;

import models.*;
import java.util.*;

/**
 * Gestor centralizado de repositorios con PERSISTENCIA usando patrón Singleton
 * Guarda y carga datos automáticamente desde archivos JSON
 */
public class RepositoryManager {

    private static RepositoryManager instance;

    private final UserRepository userRepository;
    private final AppoinmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    private final JsonPersistenceManager persistenceManager;

    /**
     * Constructor privado - carga datos automáticamente
     */
    private RepositoryManager() {
        System.out.println("════════════════════════════════════════════");
        System.out.println("   INICIALIZANDO SISTEMA CON PERSISTENCIA");
        System.out.println("════════════════════════════════════════════");

        // Inicializar gestor de persistencia
        this.persistenceManager = new JsonPersistenceManager();

        // Crear repositorios vacíos
        this.userRepository = new UserRepository();
        this.appointmentRepository = new AppoinmentRepository();
        this.patientRepository = new PatientRepository();
        this.doctorRepository = new DoctorRepository();
        this.specialityRepository = new SpecialityRepository();

        // Cargar datos desde archivos JSON
        loadAllData();

        // Registrar hook de apagado para guardar automáticamente
        registerShutdownHook();

        System.out.println("════════════════════════════════════════════");
        System.out.println("✅ Sistema inicializado correctamente");
        System.out.println("════════════════════════════════════════════\n");
    }

    /**
     * Obtiene la instancia única del gestor
     */
    public static synchronized RepositoryManager getInstance() {
        if (instance == null) {
            instance = new RepositoryManager();
        }
        return instance;
    }

    /**
     * Carga todos los datos desde archivos JSON
     */
    private void loadAllData() {
        System.out.println("\n📂 Cargando datos desde archivos JSON...\n");

        // 1. Cargar especialidades primero (las necesitan los doctores)
        List<Speciality> specialities = persistenceManager.loadSpecialities();
        Map<Integer, Speciality> specialitiesMap = new HashMap<>();
        for (Speciality speciality : specialities) {
            specialityRepository.add(speciality);
            specialitiesMap.put(speciality.getSpecialityId(), speciality);
        }

        // 2. Cargar pacientes
        List<Patient> patients = persistenceManager.loadPatients();
        Map<Integer, Patient> patientsMap = new HashMap<>();
        for (Patient patient : patients) {
            patientRepository.add(patient);
            userRepository.add(patient);
            patientsMap.put(patient.getCredentials().getId(), patient);
        }

        // 3. Cargar doctores (necesitan especialidades)
        List<Doctor> doctors = persistenceManager.loadDoctors(specialitiesMap);
        Map<Integer, Doctor> doctorsMap = new HashMap<>();
        for (Doctor doctor : doctors) {
            doctorRepository.add(doctor);
            userRepository.add(doctor);
            doctorsMap.put(doctor.getCredentials().getId(), doctor);
        }

        // 4. Cargar citas (necesitan pacientes y doctores)
        List<Appointment> appointments = persistenceManager.loadAppointments(patientsMap, doctorsMap);
        for (Appointment appointment : appointments) {
            appointmentRepository.add(appointment);
        }

        System.out.println("\n✅ Datos cargados exitosamente\n");
    }

    /**
     * Guarda todos los datos en archivos JSON
     */
    public void saveAllData() {
        System.out.println("\n💾 Guardando datos en archivos JSON...\n");

        persistenceManager.saveSpecialities(specialityRepository.getAll());
        persistenceManager.savePatients(patientRepository.getAll());
        persistenceManager.saveDoctors(doctorRepository.getAll());
        persistenceManager.saveAppointments(appointmentRepository.getAll());

        System.out.println("\n✅ Datos guardados exitosamente\n");
    }

    /**
     * Registra un hook para guardar datos al cerrar la aplicación
     */
    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n🔄 Guardando datos antes de cerrar...");
            saveAllData();
            System.out.println("👋 Sistema cerrado correctamente\n");
        }));
    }

    // ==================== GETTERS ====================

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public AppoinmentRepository getAppointmentRepository() {
        return appointmentRepository;
    }

    public PatientRepository getPatientRepository() {
        return patientRepository;
    }

    public DoctorRepository getDoctorRepository() {
        return doctorRepository;
    }

    public SpecialityRepository getSpecialityRepository() {
        return specialityRepository;
    }

    /**
     * Imprime estadísticas del sistema
     */
    public void printStats() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("       ESTADÍSTICAS DEL SISTEMA");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("👥 Pacientes:      " + patientRepository.getAll().size());
        System.out.println("⚕️  Doctores:       " + doctorRepository.getAll().size());
        System.out.println("📋 Especialidades: " + specialityRepository.getAll().size());
        System.out.println("📅 Citas:          " + appointmentRepository.getAll().size());
        System.out.println("═══════════════════════════════════════════\n");
    }

    /**
     * Fuerza el guardado inmediato de datos
     */
    public void forceSave() {
        saveAllData();
    }
}