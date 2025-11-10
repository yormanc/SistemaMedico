package interfaces.services;

import models.Speciality;
import java.util.List;

/**
 * Interface para gestión de especialidades médicas
 * Principio: Single Responsibility - Solo gestiona especialidades
 */
public interface ISpecialityService {
    
    /**
     * Agrega una nueva especialidad
     */
    boolean addSpeciality(int id, String name, String description);
    
    /**
     * Agrega una especialidad existente
     */
    boolean addSpeciality(Speciality speciality);
    
    /**
     * Actualiza una especialidad existente
     */
    boolean updateSpeciality(Speciality speciality);
    
    /**
     * Elimina una especialidad
     */
    boolean deleteSpeciality(Speciality speciality);
    
    /**
     * Busca una especialidad por ID
     */
    Speciality getSpecialityById(int specialityId);
    
    /**
     * Busca una especialidad por nombre
     */
    Speciality getSpecialityByName(String name);
    
    /**
     * Obtiene todas las especialidades
     */
    List<Speciality> getAllSpecialities();
    
    /**
     * Cuenta el número total de especialidades
     */
    int countSpecialities();
}
