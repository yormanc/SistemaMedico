package repositories;

import java.util.ArrayList;
import interfaces.IRepositorySpeciality;
import models.Speciality;

public class SpecialityRepository implements IRepositorySpeciality {
    private final ArrayList<Speciality> specialities;

    public SpecialityRepository() {
        this.specialities = new ArrayList<>();
    }

    @Override
    public boolean add(Speciality speciality) {
        try {
            // Validaciones básicas
            if (speciality == null) {
                throw new Exception("La especialidad no puede ser nula");
            }
            if (speciality.getSpecialityId() <= 0) {
                throw new Exception("El ID de la especialidad debe ser mayor a 0");
            }

            // Verificar que no exista una especialidad con el mismo ID
            boolean specialityExists = specialities.stream()
                .anyMatch(s -> s.getSpecialityId() == speciality.getSpecialityId());

            if (specialityExists) {
                throw new Exception("Ya existe una especialidad con este ID");
            }

            // Agregar la especialidad
            specialities.add(speciality);
            return true;

        } catch (Exception e) {
            System.err.println("Error al agregar la especialidad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Speciality speciality) {
        try {
            // Validaciones básicas
            if (speciality == null) {
                throw new Exception("La especialidad a actualizar no puede ser nula");
            }

            // Buscar la especialidad existente
            Speciality existingSpeciality = specialities.stream()
                .filter(s -> s.getSpecialityId() == speciality.getSpecialityId())
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró la especialidad a actualizar"));

            // Actualizar los datos
            int index = specialities.indexOf(existingSpeciality);
            specialities.set(index, speciality);
            return true;

        } catch (Exception e) {
            System.err.println("Error al actualizar la especialidad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Speciality specialityToremove) {
        try {
            if (specialities.isEmpty()) {
                throw new Exception("No hay especialidades registradas");
            }

            if (specialityToremove == null) {
                throw new Exception("La especialidad a eliminar no puede ser nula");
            }

            boolean wasRemoved = specialities.remove(specialityToremove);
            
            if (!wasRemoved) {
                throw new Exception("No se pudo eliminar la especialidad seleccionada");
            }

            return true;

        } catch (Exception e) {
            System.err.println("Error al eliminar la especialidad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Speciality searchById(int specialityId) {
        try {
            if (specialityId <= 0) {
                throw new Exception("El ID debe ser mayor a 0");
            }

            return specialities.stream()
                .filter(s -> s.getSpecialityId() == specialityId)
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró una especialidad con el ID: " + specialityId));

        } catch (Exception e) {
            System.err.println("Error al buscar la especialidad: " + e.getMessage());
            return null;
        }
    }

    // Métodos adicionales útiles
    public ArrayList<Speciality> getAll() {
        return new ArrayList<>(specialities);
    }

    public Speciality searchByName(String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("El nombre no puede estar vacío");
            }

            return specialities.stream()
                .filter(s -> s.getName() != null && 
                        s.getName().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElse(null);

        } catch (Exception e) {
            System.err.println("Error al buscar la especialidad por nombre: " + e.getMessage());
            return null;
        }
    }

    public int count() {
        return specialities.size();
    }
}
