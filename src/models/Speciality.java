package models;

public class Speciality {
    private int specialityId;
    private String name;
    private String description;

    // Constructor vacío
    public Speciality() {
    }

    // Constructor con todos los parámetros
    public Speciality(int specialityId, String name, String description) {
        this.specialityId = specialityId;
        this.name = name;
        this.description = description;
    }

    // Getters y Setters
    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
