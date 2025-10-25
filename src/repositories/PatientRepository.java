package repositories;

import java.util.ArrayList;
import interfaces.IRepositoryPatient;
import models.Patient;

public class PatientRepository implements IRepositoryPatient {
    private final ArrayList<Patient> patients;

    public PatientRepository() {
        this.patients = new ArrayList<>();
    }

    @Override
    public boolean add(Patient patient) {
        try {
            // Validaciones básicas
            if (patient == null) {
                throw new Exception("El paciente no puede ser nulo");
            }
            if (patient.getCredentials() == null) {
                throw new Exception("El paciente debe tener credenciales");
            }

            // Verificar que no exista un paciente con el mismo ID
            boolean patientExists = patients.stream()
                .anyMatch(p -> p.getCredentials().getId() == patient.getCredentials().getId());

            if (patientExists) {
                throw new Exception("Ya existe un paciente con este ID");
            }

            // Agregar el paciente
            patients.add(patient);
            return true;

        } catch (Exception e) {
            System.err.println("Error al agregar el paciente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Patient patient) {
        try {
            // Validaciones básicas
            if (patient == null) {
                throw new Exception("El paciente a actualizar no puede ser nulo");
            }
            if (patient.getCredentials() == null) {
                throw new Exception("El paciente debe tener credenciales");
            }

            // Buscar el paciente existente
            Patient existingPatient = patients.stream()
                .filter(p -> p.getCredentials().getId() == patient.getCredentials().getId())
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró el paciente a actualizar"));

            // Actualizar los datos del paciente
            int index = patients.indexOf(existingPatient);
            patients.set(index, patient);
            return true;

        } catch (Exception e) {
            System.err.println("Error al actualizar el paciente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Patient patientToDelete) {
        try {
            if (patients.isEmpty()) {
                throw new Exception("No hay pacientes registrados");
            }

            if (patientToDelete == null) {
                throw new Exception("El paciente a eliminar no puede ser nulo");
            }

            boolean wasRemoved = patients.remove(patientToDelete);

            if (!wasRemoved) {
                throw new Exception("No se pudo eliminar el paciente seleccionado");
            }

            return true;

        } catch (Exception e) {
            System.err.println("Error al eliminar el paciente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Patient searchById(int patientId) {
        try {
            if (patientId <= 0) {
                throw new Exception("El ID debe ser mayor a 0");
            }

            return patients.stream()
                .filter(p -> p.getCredentials().getId() == patientId)
                .findFirst()
                .orElseThrow(() -> new Exception("No se encontró un paciente con el ID: " + patientId));

        } catch (Exception e) {
            System.err.println("Error al buscar el paciente: " + e.getMessage());
            return null;
        }
    }

  
    public ArrayList<Patient> getAll() {
        return new ArrayList<>(patients);
    }
}
