package models;

public class Doctor {
    private int doctorId;
    private String name;
    private Speciality speciality; // relación con Speciality

    // Constructor vacío (útil para frameworks)
    public Doctor() {}

    // Constructor con parámetros
    public Doctor(int doctorId, String name, Speciality speciality) {
        this.doctorId = doctorId;
        this.name = name;
        this.speciality = speciality;
    }

    // Getters y Setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    // Método de comportamiento
    public void diagnose() {
        System.out.println("Diagnosing as a specialist in " + speciality.getName());
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", speciality=" + (speciality != null ? speciality.getName() : "None") +
                '}';
    }
}