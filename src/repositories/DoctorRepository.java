package repositories;

import java.util.ArrayList;
import interfaces.IRepositoryDoctor;
import models.Doctor;


public class DoctorRepository implements IRepositoryDoctor {
    private final ArrayList<Doctor> doctors;

    public DoctorRepository() {
        this.doctors = new ArrayList<>();
    }

    @Override
    public boolean add(Doctor doctor) {
        try {
            // Validaciones básicas
            if (doctor == null) {
                throw new Exception("El doctor no puede ser nulo");
            }
            if (doctor.getCredentials() == null) {
                throw new Exception("El doctor debe tener credenciales");
            }
            if (doctor.getSpeciality() == null) {
                throw new Exception("El doctor debe tener una especialidad asignada");
            }

            // Verificar que no exista un doctor con el mismo ID
            boolean doctorExists = doctors.stream()
                .anyMatch(d -> d.getCredentials().getId() == doctor.getCredentials().getId());

            if (doctorExists) {
                throw new Exception("Ya existe un doctor con este ID");
            }

            // Agregar el doctor
            doctors.add(doctor);
            return true;

        } catch (Exception e) {
            System.err.println("Error al agregar el doctor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Doctor doctor) {
        try {
            // Validaciones básicas
            if (doctor == null) {
                throw new Exception("El doctor a actualizar no puede ser nulo");
            }
            if (doctor.getCredentials() == null) {
                throw new Exception("El doctor debe tener credenciales");
            }

            // Buscar el doctor existente
            Doctor existingDoctor = doctors.stream()
                .filter(d -> d.getCredentials().getId() == doctor.getCredentials().getId())
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró el doctor a actualizar"));

            // Actualizar los datos del doctor
            int index = doctors.indexOf(existingDoctor);
            doctors.set(index, doctor);
            return true;

        } catch (Exception e) {
            System.err.println("Error al actualizar el doctor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Doctor doctorToremove) {
        try {
            if (doctors.isEmpty()) {
                throw new Exception("No hay doctores registrados");
            }

            if (doctorToremove == null) {
                throw new Exception("El doctor a eliminar no puede ser nulo");
            }

            boolean wasRemoved = doctors.remove(doctorToremove);

            if (!wasRemoved) {
                throw new Exception("No se pudo eliminar el doctor seleccionado");
            }

            return true;

        } catch (Exception e) {
            System.err.println("Error al eliminar el doctor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Doctor searchById(int doctorId) {
        try {
            if (doctorId <= 0) {
                throw new Exception("El ID debe ser mayor a 0");
            }

            return doctors.stream()
                .filter(d -> d.getCredentials().getId() == doctorId)
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró un doctor con el ID: " + doctorId));

        } catch (Exception e) {
            System.err.println("Error al buscar el doctor: " + e.getMessage());
            return null;
        }
    }
    public ArrayList<Doctor> getAll() {
        return new ArrayList<>(doctors);
    }

  

}

