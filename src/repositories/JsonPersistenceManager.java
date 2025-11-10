package repositories;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import models.*;
import dto.*;
import dto.adapters.LocalDateTimeAdapter;
import utils.Constants;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Gestor de persistencia JSON para el sistema médico
 * ACTUALIZADO: Usa DTOs externos del paquete dto/
 */
public class JsonPersistenceManager {

    private final Gson gson;

    public JsonPersistenceManager() {
        // Configurar Gson con adaptador para LocalDateTime
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
            Path dataPath = Paths.get(Constants.DATA_DIR);
            if (!Files.exists(dataPath)) {
                Files.createDirectories(dataPath);
                System.out.println("Directorio de datos creado: " + Constants.DATA_DIR);
            }
        } catch (IOException e) {
            System.err.println("Error al crear directorio de datos: " + e.getMessage());
        }
    }

    // ==================== PACIENTES ====================

    /**
     * Guarda la lista de pacientes en JSON
     */
    public void savePatients(List<Patient> patients) {
        try {
            // Convertir pacientes a DTOs
            List<PatientDTO> patientDTOs = new ArrayList<>();
            for (Patient patient : patients) {
                patientDTOs.add(PatientDTO.fromPatient(patient));
            }

            String json = gson.toJson(patientDTOs);
            Files.writeString(Paths.get(Constants.PATIENTS_FILE), json);
            System.out.println("Pacientes guardados: " + patients.size());
        } catch (IOException e) {
            System.err.println("Error al guardar pacientes: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de pacientes desde JSON
     */
    public List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(Constants.PATIENTS_FILE))) {
                System.out.println("No existe archivo de pacientes, iniciando vacío");
                return patients;
            }

            String json = Files.readString(Paths.get(Constants.PATIENTS_FILE));
            List<PatientDTO> patientDTOs = gson.fromJson(json,
                    new TypeToken<List<PatientDTO>>(){}.getType());

            if (patientDTOs != null) {
                for (PatientDTO dto : patientDTOs) {
                    patients.add(dto.toPatient());
                }
            }

            System.out.println("Pacientes cargados: " + patients.size());
        } catch (IOException e) {
            System.err.println("Error al cargar pacientes: " + e.getMessage());
        }
        return patients;
    }

    // ==================== DOCTORES ====================

    /**
     * Guarda la lista de doctores en JSON
     */
    public void saveDoctors(List<Doctor> doctors) {
        try {
            // Convertir doctores a DTOs
            List<DoctorDTO> doctorDTOs = new ArrayList<>();
            for (Doctor doctor : doctors) {
                doctorDTOs.add(DoctorDTO.fromDoctor(doctor));
            }

            String json = gson.toJson(doctorDTOs);
            Files.writeString(Paths.get(Constants.DOCTORS_FILE), json);
            System.out.println("Doctores guardados: " + doctors.size());
        } catch (IOException e) {
            System.err.println("Error al guardar doctores: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de doctores desde JSON
     */
    public List<Doctor> loadDoctors(Map<Integer, Speciality> specialitiesMap) {
        List<Doctor> doctors = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(Constants.DOCTORS_FILE))) {
                System.out.println("No existe archivo de doctores, iniciando vacío");
                return doctors;
            }

            String json = Files.readString(Paths.get(Constants.DOCTORS_FILE));
            List<DoctorDTO> doctorDTOs = gson.fromJson(json,
                    new TypeToken<List<DoctorDTO>>(){}.getType());

            if (doctorDTOs != null) {
                for (DoctorDTO dto : doctorDTOs) {
                    doctors.add(dto.toDoctor(specialitiesMap));
                }
            }

            System.out.println("Doctores cargados: " + doctors.size());
        } catch (IOException e) {
            System.err.println("Error al cargar doctores: " + e.getMessage());
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
            Files.writeString(Paths.get(Constants.SPECIALITIES_FILE), json);
            System.out.println("Especialidades guardadas: " + specialities.size());
        } catch (IOException e) {
            System.err.println("Error al guardar especialidades: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de especialidades desde JSON
     */
    public List<Speciality> loadSpecialities() {
        List<Speciality> specialities = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(Constants.SPECIALITIES_FILE))) {
                System.out.println("No existe archivo de especialidades, iniciando vacío");
                return specialities;
            }

            String json = Files.readString(Paths.get(Constants.SPECIALITIES_FILE));
            specialities = gson.fromJson(json,
                    new TypeToken<List<Speciality>>(){}.getType());

            if (specialities == null) specialities = new ArrayList<>();
            System.out.println("Especialidades cargadas: " + specialities.size());
        } catch (IOException e) {
            System.err.println("Error al cargar especialidades: " + e.getMessage());
        }
        return specialities;
    }

    // ==================== CITAS ====================

    /**
     * Guarda la lista de citas en JSON
     */
    public void saveAppointments(List<Appointment> appointments) {
        try {
            // Convertir citas a DTOs
            List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
            for (Appointment appointment : appointments) {
                appointmentDTOs.add(AppointmentDTO.fromAppointment(appointment));
            }

            String json = gson.toJson(appointmentDTOs);
            Files.writeString(Paths.get(Constants.APPOINTMENTS_FILE), json);
            System.out.println("Citas guardadas: " + appointments.size());
        } catch (IOException e) {
            System.err.println("Error al guardar citas: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de citas desde JSON
     */
    public List<Appointment> loadAppointments(Map<Integer, Patient> patientsMap,
                                              Map<Integer, Doctor> doctorsMap) {
        List<Appointment> appointments = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(Constants.APPOINTMENTS_FILE))) {
                System.out.println("No existe archivo de citas, iniciando vacío");
                return appointments;
            }

            String json = Files.readString(Paths.get(Constants.APPOINTMENTS_FILE));
            List<AppointmentDTO> appointmentDTOs = gson.fromJson(json,
                    new TypeToken<List<AppointmentDTO>>(){}.getType());

            if (appointmentDTOs != null) {
                for (AppointmentDTO dto : appointmentDTOs) {
                    appointments.add(dto.toAppointment(patientsMap, doctorsMap));
                }
            }

            System.out.println("Citas cargadas: " + appointments.size());
        } catch (IOException e) {
            System.err.println("Error al cargar citas: " + e.getMessage());
        }
        return appointments;
    }
}