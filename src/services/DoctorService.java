package services;

import interfaces.services.IDoctorService;
import repositories.DoctorRepository;
import repositories.UserRepository;
import models.Doctor;
import models.Speciality;
import models.Credentials;
import enums.UserRole;
import repositories.RepositoryManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para gestión de doctores
 * Responsabilidad: Solo operaciones CRUD de doctores
 */
public class DoctorService implements IDoctorService {
    
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    
    public DoctorService(DoctorRepository doctorRepository, UserRepository userRepository) {
        if (doctorRepository == null) {
            throw new IllegalArgumentException("El repositorio de doctores no puede ser nulo");
        }
        if (userRepository == null) {
            throw new IllegalArgumentException("El repositorio de usuarios no puede ser nulo");
        }
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public boolean registerDoctor(String fullName, int id, String password, 
                                  int age, String email, Speciality speciality) {
        try {
            // Validaciones básicas
            if (fullName == null || fullName.trim().isEmpty()) {
                System.err.println("El nombre no puede estar vacío");
                return false;
            }
            
            if (speciality == null) {
                System.err.println("El doctor debe tener una especialidad");
                return false;
            }
            
            // Crear credenciales y doctor
            Credentials credentials = new Credentials(id, password, UserRole.DOCTOR);
            Doctor doctor = new Doctor(fullName, age, email, credentials, speciality);
            
            // Agregar a repositorio de doctores
            boolean addedToDoctor = doctorRepository.add(doctor);
            if (!addedToDoctor) {
                System.err.println("No se pudo agregar al repositorio de doctores");
                return false;
            }
            
            // Agregar a repositorio de usuarios
            boolean addedToUser = userRepository.add(doctor);
            if (!addedToUser) {
                // Rollback: remover de repositorio de doctores
                doctorRepository.remove(doctor);
                System.err.println("El ID ya existe en el sistema de usuarios");
                return false;
            }
            
            // Guardar cambios
            RepositoryManager.getInstance().forceSave();
            
            System.out.println("Doctor registrado: " + fullName + " (ID: " + id + ")");
            System.out.println("Especialidad: " + speciality.getName());
            System.out.println("Total de doctores: " + doctorRepository.getAll().size());
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al registrar doctor: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean updateDoctor(Doctor doctor) {
        try {
            if (doctor == null) {
                System.err.println("El doctor no puede ser nulo");
                return false;
            }
            
            // Actualizar en repositorio de doctores
            boolean updatedInDoctor = doctorRepository.update(doctor);
            if (!updatedInDoctor) {
                System.err.println("No se pudo actualizar en repositorio de doctores");
                return false;
            }
            
            // Actualizar en repositorio de usuarios
            boolean updatedInUser = userRepository.update(doctor);
            if (!updatedInUser) {
                System.err.println("Advertencia: No se actualizó en repositorio de usuarios");
            }
            
            // Guardar cambios
            RepositoryManager.getInstance().forceSave();
            
            System.out.println("Doctor actualizado: " + doctor.getFullName());
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al actualizar doctor: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean deleteDoctor(Doctor doctor) {
        try {
            if (doctor == null) {
                System.err.println("El doctor no puede ser nulo");
                return false;
            }
            
            // Eliminar de repositorio de doctores
            boolean removedFromDoctor = doctorRepository.remove(doctor);
            if (!removedFromDoctor) {
                System.err.println("No se pudo eliminar del repositorio de doctores");
                return false;
            }
            
            // Eliminar de repositorio de usuarios
            boolean removedFromUser = userRepository.remove(doctor);
            if (!removedFromUser) {
                System.err.println("Advertencia: No se eliminó del repositorio de usuarios");
            }
            
            // Guardar cambios
            RepositoryManager.getInstance().forceSave();
            
            System.out.println("Doctor eliminado: " + doctor.getFullName());
            System.out.println("Total de doctores: " + doctorRepository.getAll().size());
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al eliminar doctor: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Doctor getDoctorById(int doctorId) {
        try {
            return doctorRepository.searchById(doctorId);
        } catch (Exception e) {
            System.err.println("Error al buscar doctor: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Doctor> getAllDoctors() {
        try {
            return doctorRepository.getAll();
        } catch (Exception e) {
            System.err.println("Error al obtener doctores: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Doctor> getDoctorsBySpeciality(Speciality speciality) {
        try {
            if (speciality == null) {
                System.err.println("La especialidad no puede ser nula");
                return List.of();
            }
            
            return doctorRepository.getAll().stream()
                .filter(doctor -> doctor.getSpeciality() != null && 
                                 doctor.getSpeciality().getSpecialityId() == speciality.getSpecialityId())
                .collect(Collectors.toList());
                
        } catch (Exception e) {
            System.err.println("Error al obtener doctores por especialidad: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public boolean doctorExists(int doctorId) {
        try {
            Doctor doctor = doctorRepository.searchById(doctorId);
            return doctor != null;
        } catch (Exception e) {
            return false;
        }
    }
}