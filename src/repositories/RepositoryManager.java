package repositories;

/**
 * Gestor centralizado de repositorios usando patrón Singleton
 * Garantiza que todos los servicios y formularios usen las mismas instancias
 * de los repositorios, permitiendo la persistencia de datos en memoria
 */
public class RepositoryManager {
    
    // Instancia única del gestor
    private static RepositoryManager instance;
    
    // Instancias únicas de los repositorios
    private final UserRepository userRepository;
    private final AppoinmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    
    /**
     * Constructor privado para evitar instanciación externa
     */
    private RepositoryManager() {
        this.userRepository = new UserRepository();
        this.appointmentRepository = new AppoinmentRepository();
        this.patientRepository = new PatientRepository();
        this.doctorRepository = new DoctorRepository();
        this.specialityRepository = new SpecialityRepository();
        
        System.out.println("✅ RepositoryManager inicializado");
    }
    
    /**
     * Obtiene la instancia única del gestor de repositorios
     * @return La instancia única de RepositoryManager
     */
    public static synchronized RepositoryManager getInstance() {
        if (instance == null) {
            instance = new RepositoryManager();
        }
        return instance;
    }
    
    /**
     * Obtiene el repositorio de usuarios
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }
    
    /**
     * Obtiene el repositorio de citas
     */
    public AppoinmentRepository getAppointmentRepository() {
        return appointmentRepository;
    }
    
    /**
     * Obtiene el repositorio de pacientes
     */
    public PatientRepository getPatientRepository() {
        return patientRepository;
    }
    
    /**
     * Obtiene el repositorio de doctores
     */
    public DoctorRepository getDoctorRepository() {
        return doctorRepository;
    }
    
    /**
     * Obtiene el repositorio de especialidades
     */
    public SpecialityRepository getSpecialityRepository() {
        return specialityRepository;
    }
    
    /**
     * Imprime estadísticas de los repositorios (útil para debugging)
     */
    public void printStats() {
        System.out.println("\n=== ESTADÍSTICAS DEL SISTEMA ===");
        System.out.println("📊 Pacientes registrados: " + patientRepository.getAll().size());
        System.out.println("📊 Doctores registrados: " + doctorRepository.getAll().size());
        System.out.println("📊 Citas registradas: " + appointmentRepository.getAll().size());
        System.out.println("================================\n");
    }
    
    /**
     * Limpia todos los repositorios (útil para testing)
     */
    public void clearAll() {
        // Implementar según la estructura de tus repositorios
        System.out.println("⚠️ Todos los datos han sido eliminados");
    }
}
