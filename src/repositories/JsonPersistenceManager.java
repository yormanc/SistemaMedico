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
 * Gestor de persistencia JSON MEJORADO
 * Ahora sincroniza correctamente los IDs auto-incrementales
 */
public class JsonPersistenceManager {

    private final Gson gson;

    public JsonPersistenceManager() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();

        createDataDirectory();
    }

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

    public void savePatients(List<Patient> patients) {
        try {
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

    public void saveDoctors(List<Doctor> doctors) {
        try {
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

    public void saveSpecialities(List<Speciality> specialities) {
        try {
            String json = gson.toJson(specialities);
            Files.writeString(Paths.get(Constants.SPECIALITIES_FILE), json);
            System.out.println("Especialidades guardadas: " + specialities.size());
        } catch (IOException e) {
            System.err.println("Error al guardar especialidades: " + e.getMessage());
        }
    }

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

    // ==================== CITAS (MEJORADO) ====================

    public void saveAppointments(List<Appointment> appointments) {
        try {
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
     * MEJORADO: Ahora sincroniza el contador de IDs automáticamente
     */
    public List<Appointment> loadAppointments(Map<Integer, Patient> patientsMap,
                                              Map<Integer, Doctor> doctorsMap) {
        List<Appointment> appointments = new ArrayList<>();
        int maxId = 0;
        
        try {
            if (!Files.exists(Paths.get(Constants.APPOINTMENTS_FILE))) {
                System.out.println("No existe archivo de citas, iniciando vacío");
                return appointments;
            }

            String json = Files.readString(Paths.get(Constants.APPOINTMENTS_FILE));
            List<AppointmentDTO> appointmentDTOs = gson.fromJson(json,
                    new TypeToken<List<AppointmentDTO>>(){}.getType());

            if (appointmentDTOs != null) {
                // Primero, detectar duplicados por ID
                Set<Integer> seenIds = new HashSet<>();
                List<AppointmentDTO> uniqueDTOs = new ArrayList<>();
                
                for (AppointmentDTO dto : appointmentDTOs) {
                    if (!seenIds.contains(dto.getAppointmentId())) {
                        seenIds.add(dto.getAppointmentId());
                        uniqueDTOs.add(dto);
                        
                        // Rastrear el ID más alto
                        if (dto.getAppointmentId() > maxId) {
                            maxId = dto.getAppointmentId();
                        }
                    } else {
                        System.out.println("⚠️  Cita duplicada detectada y omitida - ID: " + 
                                         dto.getAppointmentId());
                    }
                }
                
                // Convertir DTOs únicos a Appointments
                for (AppointmentDTO dto : uniqueDTOs) {
                    try {
                        Appointment appointment = dto.toAppointment(patientsMap, doctorsMap);
                        appointments.add(appointment);
                    } catch (Exception e) {
                        System.err.println("️  Error al cargar cita ID " +
                                         dto.getAppointmentId() + ": " + e.getMessage());
                    }
                }
            }

            // CRÍTICO: Sincronizar el contador de IDs
            if (maxId > 0) {
                // Usar reflexión para actualizar el nextId estático
                syncAppointmentIdCounter(maxId + 1);
                System.out.println(" Contador de IDs sincronizado. Próximo ID: " + (maxId + 1));
            }

            System.out.println("Citas cargadas: " + appointments.size());
            
        } catch (IOException e) {
            System.err.println("Error al cargar citas: " + e.getMessage());
        }
        
        return appointments;
    }

    /**
     * NUEVO: Sincroniza el contador estático de IDs de Appointment
     */
    private void syncAppointmentIdCounter(int nextId) {
        try {
            // Usar el método estático de Appointment para resetear
            // Primero resetear a 1
            Appointment.resetIdCounter();
            
            // Luego crear citas dummy para llegar al ID correcto
            for (int i = 1; i < nextId; i++) {
                new Appointment(); // Esto incrementa el contador interno
            }
            
            System.out.println(" Contador de citas sincronizado exitosamente");
            
        } catch (Exception e) {
            System.err.println(" Advertencia: No se pudo sincronizar el contador de IDs: " +
                             e.getMessage());
        }
    }

    /**
     * NUEVO: Limpia citas duplicadas de un archivo
     */
    public void cleanDuplicateAppointments() {
        try {
            if (!Files.exists(Paths.get(Constants.APPOINTMENTS_FILE))) {
                return;
            }

            String json = Files.readString(Paths.get(Constants.APPOINTMENTS_FILE));
            List<AppointmentDTO> appointmentDTOs = gson.fromJson(json,
                    new TypeToken<List<AppointmentDTO>>(){}.getType());

            if (appointmentDTOs == null || appointmentDTOs.isEmpty()) {
                return;
            }

            // Eliminar duplicados manteniendo el primero
            Set<Integer> seenIds = new HashSet<>();
            List<AppointmentDTO> cleanDTOs = new ArrayList<>();
            int duplicatesRemoved = 0;

            for (AppointmentDTO dto : appointmentDTOs) {
                if (!seenIds.contains(dto.getAppointmentId())) {
                    seenIds.add(dto.getAppointmentId());
                    cleanDTOs.add(dto);
                } else {
                    duplicatesRemoved++;
                }
            }

            if (duplicatesRemoved > 0) {
                // Guardar archivo limpio
                String cleanJson = gson.toJson(cleanDTOs);
                Files.writeString(Paths.get(Constants.APPOINTMENTS_FILE), cleanJson);
                System.out.println(" Limpieza completada: " + duplicatesRemoved +
                                 " citas duplicadas eliminadas");
            } else {
                System.out.println(" No se encontraron citas duplicadas");
            }

        } catch (IOException e) {
            System.err.println("Error al limpiar duplicados: " + e.getMessage());
        }
    }
}