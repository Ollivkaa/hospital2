package model;

public class Nurse extends MedicalStaff {
    private int patientsAssigned;

    public Nurse(int id, String name, String dept, int exp, int patients) {
        super(id, name, dept, exp);
        setPatientsAssigned(patients);
    }

    @Override
    public void work() {
        System.out.println("Nurse " + name + " is caring for patients");
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    public void setPatientsAssigned(int patientsAssigned) {
        if (patientsAssigned < 0)
            throw new IllegalArgumentException("Patients cannot be negative");
        this.patientsAssigned = patientsAssigned;
    }
}
