package repositories;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import models.Doctor;

public class DoctorRepository {

    private final List<Doctor> doctors = new ArrayList<>();

    // Agregar un doctor
    public void add(Doctor doctor) {
        doctors.add(doctor);
    }

    // Buscar doctor por id
    public Optional<Doctor> findById(int id) {
        return doctors.stream()
                .filter(d -> d.getDoctorId() == id)
                .findFirst();
    }

    // Listar todos los doctores
    public List<Doctor> findAll() {
        return Collections.unmodifiableList(doctors);
    }

    // Eliminar doctor por id
    public void remove(int id) {
        doctors.removeIf(d -> d.getDoctorId() == id);
    }
}