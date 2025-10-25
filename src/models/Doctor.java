// Clase Doctor (Open/Closed, Single Responsibility)
public class Doctor {
    private Specialty specialty;

    public Doctor(Specialty specialty) {
        this.specialty = specialty;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void diagnose() {
        System.out.println("Diagnosing as a specialist in " + specialty.getName());
    }
}
