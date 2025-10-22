package models;

public class Medic {
    private int medicId;
    private String name;
    private Speciality speciality;
    private String email;

    // Constructor vacío
    public Medic() {
    }

    // Constructor con todos los parámetros
    public Medic(int medicId, String name, Speciality speciality, String email) {
        this.medicId = medicId;
        this.name = name;
        this.speciality = speciality;
        this.email = email;
    }

    // Getters y Setters
    public int getMedicId() {
        return medicId;
    }

    public void setMedicId(int medicId) {
        this.medicId = medicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
