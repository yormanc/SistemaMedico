package repositories;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import models.Speciality;

public class SpecialityRepository {

    private final List<Speciality> specialities = new ArrayList<>();

    public void add(Speciality speciality) {
        specialities.add(speciality);
    }

    public Optional<Speciality> findById(int id) {
        return specialities.stream()
                .filter(s -> s.getSpecialityId() == id)
                .findFirst();
    }

    public List<Speciality> findAll() {
        return Collections.unmodifiableList(specialities);
    }

    public void remove(int id) {
        specialities.removeIf(s -> s.getSpecialityId() == id);
    }
}