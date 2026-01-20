package model;

public class Appointment {
    private int id;

    public Appointment(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("Invalid appointment ID");
        this.id = id;
    }
}
