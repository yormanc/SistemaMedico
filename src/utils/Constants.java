package utils;

/**
 * Constantes del sistema
 */
public final class Constants {
    
    // Constructor privado para evitar instanciación
    private Constants() {
        throw new AssertionError("No se puede instanciar la clase Constants");
    }
    
    // ==================== PERSISTENCIA ====================
    public static final String DATA_DIR = "data";
    public static final String PATIENTS_FILE = DATA_DIR + "/patients.json";
    public static final String DOCTORS_FILE = DATA_DIR + "/doctors.json";
    public static final String SPECIALITIES_FILE = DATA_DIR + "/specialities.json";
    public static final String APPOINTMENTS_FILE = DATA_DIR + "/appointments.json";
    
    // ==================== VALIDACIÓN ====================
    public static final int MIN_PASSWORD_LENGTH = 4;
    public static final int MAX_PASSWORD_LENGTH = 50;
    public static final int MIN_NAME_LENGTH = 3;
    public static final int MIN_AGE = 0;
    public static final int MAX_AGE = 150;
    public static final int MIN_USER_ID = 1;
    
    // ==================== MENSAJES ====================
    public static final String MSG_SYSTEM_INITIALIZED = "Sistema inicializado correctamente";
    public static final String MSG_LOADING_DATA = "Cargando datos desde archivos JSON...";
    public static final String MSG_SAVING_DATA = "Guardando datos en archivos JSON...";
    public static final String MSG_DATA_SAVED = "Datos guardados exitosamente";
    public static final String MSG_SHUTDOWN = "Guardando datos antes de cerrar...";
    public static final String MSG_GOODBYE = "Sistema cerrado correctamente";
}