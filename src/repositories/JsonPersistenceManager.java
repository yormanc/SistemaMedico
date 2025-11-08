package repositories;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import models.*;
import enumerations.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Gestor de persistencia JSON para el sistema médico
 * Maneja el guardado y carga de datos desde archivos JSON
 */
public class JsonPersistenceManager {

    private static final String DATA_DIR = "data";
    private static final String PATIENTS_FILE = DATA_DIR + "/patients.json";
    private static final String DOCTORS_FILE = DATA_DIR + "/doctors.json";
    private static final String SPECIALITIES_FILE = DATA_DIR + "/specialities.json";
    private static final String APPOINTMENTS_FILE = DATA_DIR + "/appointments.json";

    private final Gson gson;

    public JsonPersistenceManager() {
        // Configurar Gson con adaptadores personalizados
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();

        // Crear directorio de datos si no existe
        createDataDirectory();
    }

    /**
     * Crea el directorio de datos si no existe
     */
    private void createDataDirectory() {
        try {
            Path dataPath = Paths.get(DATA_DIR);
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
                System.out.println("✅ Directorio de datos creado: " + DATA_DIR);
            }
        } catch (IOException e) {
            System.err.println("❌ Error al crear directorio de datos: " + e.getMessage());
        }
    }

    // ==================== PACIENTES ====================

    /**
     * Guarda la lista de pacientes en JSON
     */
    public void savePatients(List<Patient> patients) {
        try {
            // Convertir pacientes a DTOs para serialización
            List<PatientDTO> patientDTOs = new ArrayList<>();
            for (Patient patient : patients) {
                patientDTOs.add(PatientDTO.fromPatient(patient));
            }

            String json = gson.toJson(patientDTOs);
            Files.writeString(Paths.get(PATIENTS_FILE), json);
            System.out.println("✅ Pacientes guardados: " + patients.size());
        } catch (IOException e) {
            System.err.println("❌ Error al guardar pacientes: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de pacientes desde JSON
     */
    public List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(PATIENTS_FILE))) {
                System.out.println("ℹ️ No existe archivo de pacientes, iniciando vacío");
                return patients;
            }

            String json = Files.readString(Paths.get(PATIENTS_FILE));
            List<PatientDTO> patientDTOs = gson.fromJson(json,
                    new TypeToken<List<PatientDTO>>(){}.getType());

            if (patientDTOs != null) {
                for (PatientDTO dto : patientDTOs) {
                    patients.add(dto.toPatient());
                }
            }

            System.out.println("✅ Pacientes cargados: " + patients.size());
        } catch (IOException e) {
            System.err.println("❌ Error al cargar pacientes: " + e.getMessage());
        }
        return patients;
    }

    // ==================== DOCTORES ====================

    /**
     * Guarda la lista de doctores en JSON
     */
    public void saveDoctors(List<Doctor> doctors) {
        try {
            List<DoctorDTO> doctorDTOs = new ArrayList<>();
            for (Doctor doctor : doctors) {
                doctorDTOs.add(DoctorDTO.fromDoctor(doctor));
            }

            String json = gson.toJson(doctorDTOs);
            Files.writeString(Paths.get(DOCTORS_FILE), json);
            System.out.println("✅ Doctores guardados: " + doctors.size());
        } catch (IOException e) {
            System.err.println("❌ Error al guardar doctores: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de doctores desde JSON
     */
    public List<Doctor> loadDoctors(Map<Integer, Speciality> specialitiesMap) {
        List<Doctor> doctors = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(DOCTORS_FILE))) {
                System.out.println("ℹ️ No existe archivo de doctores, iniciando vacío");
                return doctors;
            }

            String json = Files.readString(Paths.get(DOCTORS_FILE));
            List<DoctorDTO> doctorDTOs = gson.fromJson(json,
                    new TypeToken<List<DoctorDTO>>(){}.getType());

            if (doctorDTOs != null) {
                for (DoctorDTO dto : doctorDTOs) {
                    doctors.add(dto.toDoctor(specialitiesMap));
                }
            }

            System.out.println("✅ Doctores cargados: " + doctors.size());
        } catch (IOException e) {
            System.err.println("❌ Error al cargar doctores: " + e.getMessage());
        }
        return doctors;
    }

    // ==================== ESPECIALIDADES ====================

    /**
     * Guarda la lista de especialidades en JSON
     */
    public void saveSpecialities(List<Speciality> specialities) {
        try {
            String json = gson.toJson(specialities);
            Files.writeString(Paths.get(SPECIALITIES_FILE), json);
            System.out.println("✅ Especialidades guardadas: " + specialities.size());
        } catch (IOException e) {
            System.err.println("❌ Error al guardar especialidades: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de especialidades desde JSON
     */
    public List<Speciality> loadSpecialities() {
        List<Speciality> specialities = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(SPECIALITIES_FILE))) {
                System.out.println("ℹ️ No existe archivo de especialidades, iniciando vacío");
                return specialities;
            }

            String json = Files.readString(Paths.get(SPECIALITIES_FILE));
            specialities = gson.fromJson(json,
                    new TypeToken<List<Speciality>>(){}.getType());

            if (specialities == null) specialities = new ArrayList<>();
            System.out.println("✅ Especialidades cargadas: " + specialities.size());
        } catch (IOException e) {
            System.err.println("❌ Error al cargar especialidades: " + e.getMessage());
        }
        return specialities;
    }

    // ==================== CITAS ====================

    /**
     * Guarda la lista de citas en JSON
     */
    public void saveAppointments(List<Appointment> appointments) {
        try {
            List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
            for (Appointment appointment : appointments) {
                appointmentDTOs.add(AppointmentDTO.fromAppointment(appointment));
            }

            String json = gson.toJson(appointmentDTOs);
            Files.writeString(Paths.get(APPOINTMENTS_FILE), json);
            System.out.println("✅ Citas guardadas: " + appointments.size());
        } catch (IOException e) {
            System.err.println("❌ Error al guardar citas: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de citas desde JSON
     */
    public List<Appointment> loadAppointments(Map<Integer, Patient> patientsMap,
                                              Map<Integer, Doctor> doctorsMap) {
        List<Appointment> appointments = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(APPOINTMENTS_FILE))) {
                System.out.println("ℹ️ No existe archivo de citas, iniciando vacío");
                return appointments;
            }

            String json = Files.readString(Paths.get(APPOINTMENTS_FILE));
            List<AppointmentDTO> appointmentDTOs = gson.fromJson(json,
                    new TypeToken<List<AppointmentDTO>>(){}.getType());

            if (appointmentDTOs != null) {
                for (AppointmentDTO dto : appointmentDTOs) {
                    appointments.add(dto.toAppointment(patientsMap, doctorsMap));
                }
            }

            System.out.println("✅ Citas cargadas: " + appointments.size());
        } catch (IOException e) {
            System.err.println("❌ Error al cargar citas: " + e.getMessage());
        }
        return appointments;
    }

    // ==================== CLASES DTO ====================

    /**
     * DTO para serializar/deserializar Pacientes
     */
    private static class PatientDTO {
        String fullName;
        int age;
        String email;
        int id;
        String password;
        String role;

        static PatientDTO fromPatient(Patient patient) {
            PatientDTO dto = new PatientDTO();
            dto.fullName = patient.getFullName();
            dto.age = patient.getAge();
            dto.email = patient.getEmail();
            dto.id = patient.getCredentials().getId();
            dto.password = patient.getCredentials().getPassword();
            dto.role = patient.getCredentials().getRole().name();
            return dto;
        }

        Patient toPatient() {
            Credentials credentials = new Credentials(id, password, UserRole.valueOf(role));
            return new Patient(fullName, age, email, credentials);
        }
    }

    /**
     * DTO para serializar/deserializar Doctores
     */
    private static class DoctorDTO {
        String fullName;
        int age;
        String email;
        int id;
        String password;
        String role;
        int specialityId;

        static DoctorDTO fromDoctor(Doctor doctor) {
            DoctorDTO dto = new DoctorDTO();
            dto.fullName = doctor.getFullName();
            dto.age = doctor.getAge();
            dto.email = doctor.getEmail();
            dto.id = doctor.getCredentials().getId();
            dto.password = doctor.getCredentials().getPassword();
            dto.role = doctor.getCredentials().getRole().name();
            dto.specialityId = doctor.getSpeciality().getSpecialityId();
            return dto;
        }

        Doctor toDoctor(Map<Integer, Speciality> specialitiesMap) {
            Credentials credentials = new Credentials(id, password, UserRole.valueOf(role));
            Speciality speciality = specialitiesMap.get(specialityId);
            if (speciality == null) {
                throw new RuntimeException("Especialidad no encontrada: " + specialityId);
            }
            return new Doctor(fullName, age, email, credentials, speciality);
        }
    }

    /**
     * DTO para serializar/deserializar Citas
     */
    private static class AppointmentDTO {
        int appointmentId;
        LocalDateTime dateTime;
        int patientId;
        int doctorId;
        String diagnostic;
        String status;

        static AppointmentDTO fromAppointment(Appointment appointment) {
            AppointmentDTO dto = new AppointmentDTO();
            dto.appointmentId = appointment.getAppointmentId();
            dto.dateTime = appointment.getDateTime();
            dto.patientId = appointment.getPatient().getCredentials().getId();
            dto.doctorId = appointment.getDoctor().getCredentials().getId();
            dto.diagnostic = appointment.getDiagnostic();
            dto.status = appointment.getStatus().name();
            return dto;
        }

        Appointment toAppointment(Map<Integer, Patient> patientsMap,
                                  Map<Integer, Doctor> doctorsMap) {
            Patient patient = patientsMap.get(patientId);
            Doctor doctor = doctorsMap.get(doctorId);

            if (patient == null) {
                throw new RuntimeException("Paciente no encontrado: " + patientId);
            }
            if (doctor == null) {
                throw new RuntimeException("Doctor no encontrado: " + doctorId);
            }

            return new Appointment(appointmentId, dateTime, patient, doctor,
                    diagnostic, AppoinmnetStatus.valueOf(status));
        }
    }

    /**
     * Adaptador para serializar/deserializar LocalDateTime
     */
    private static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>,
            JsonDeserializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime src, java.lang.reflect.Type typeOfSrc,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }

        @Override
        public LocalDateTime deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
                                         JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString());
        }
    }
}