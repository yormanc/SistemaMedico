package services;

import interfaces.services.ISpecialityService;
import repositories.SpecialityRepository;
import models.Speciality;
import repositories.RepositoryManager;
import java.util.List;

/**
 * Servicio para gestión de especialidades médicas
 * Responsabilidad: Solo operaciones CRUD de especialidades
 */
public class SpecialityService implements ISpecialityService {
    
    private final SpecialityRepository specialityRepository;
    
    public SpecialityService(SpecialityRepository specialityRepository) {
        if (specialityRepository == null) {
            throw new IllegalArgumentException("El repositorio de especialidades no puede ser nulo");
        }
        this.specialityRepository = specialityRepository;
    }
    
    @Override
    public boolean addSpeciality(int id, String name, String description) {
        try {
            if (name == null || name.trim().isEmpty()) {
                System.err.println("El nombre de la especialidad no puede estar vacío");
                return false;
            }
            
            Speciality speciality = new Speciality(id, name, description);
            return addSpeciality(speciality);
            
        } catch (Exception e) {
            System.err.println("Error al crear especialidad: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean addSpeciality(Speciality speciality) {
        try {
            if (speciality == null) {
                System.err.println("La especialidad no puede ser nula");
                return false;
            }
            
            boolean added = specialityRepository.add(speciality);
            
            if (added) {
                RepositoryManager.getInstance().forceSave();
                System.out.println("Especialidad agregada: " + speciality.getName());
            }
            
            return added;
            
        } catch (Exception e) {
            System.err.println("Error al agregar especialidad: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean updateSpeciality(Speciality speciality) {
        try {
            if (speciality == null) {
                System.err.println("La especialidad no puede ser nula");
                return false;
            }
            
            boolean updated = specialityRepository.update(speciality);
            
            if (updated) {
                RepositoryManager.getInstance().forceSave();
                System.out.println("Especialidad actualizada: " + speciality.getName());
            }
            
            return updated;
            
        } catch (Exception e) {
            System.err.println("Error al actualizar especialidad: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean deleteSpeciality(Speciality speciality) {
        try {
            if (speciality == null) {
                System.err.println("La especialidad no puede ser nula");
                return false;
            }
            
            boolean removed = specialityRepository.remove(speciality);
            
            if (removed) {
                RepositoryManager.getInstance().forceSave();
                System.out.println("Especialidad eliminada: " + speciality.getName());
            }
            
            return removed;
            
        } catch (Exception e) {
            System.err.println("Error al eliminar especialidad: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Speciality getSpecialityById(int specialityId) {
        try {
            return specialityRepository.searchById(specialityId);
        } catch (Exception e) {
            System.err.println("Error al buscar especialidad: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public Speciality getSpecialityByName(String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                System.err.println("El nombre no puede estar vacío");
                return null;
            }
            return specialityRepository.searchByName(name);
        } catch (Exception e) {
            System.err.println("Error al buscar especialidad por nombre: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Speciality> getAllSpecialities() {
        try {
            return specialityRepository.getAll();
        } catch (Exception e) {
            System.err.println("Error al obtener especialidades: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public int countSpecialities() {
        try {
            return specialityRepository.count();
        } catch (Exception e) {
            System.err.println("Error al contar especialidades: " + e.getMessage());
            return 0;
        }
    }
}
