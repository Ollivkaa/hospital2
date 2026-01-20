package model;

public class Patient {
    private int id;
    private String name;

    public Patient(int id, String name) {
        if (id <= 0) throw new IllegalArgumentException("Invalid ID");
        if (name.isEmpty()) throw new IllegalArgumentException("Name empty");
        this.id = id;
        this.name = name;
    }
}
