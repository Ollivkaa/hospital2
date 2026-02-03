package model;

public class Nurse extends MedicalStaff {
    private int patientsAssigned;

    public Nurse(int staffId, String name, String department, int experienceYears, int patientsAssigned) {
        super(staffId, name, department, experienceYears);
        setPatientsAssigned(patientsAssigned);
    }

    public int getPatientsAssigned() { return patientsAssigned; }

    public void setPatientsAssigned(int patientsAssigned) {
        if (patientsAssigned < 0)
            throw new IllegalArgumentException("Patients cannot be negative");
        this.patientsAssigned = patientsAssigned;
    }

    @Override
    public void work() {
        System.out.println(name + " is assisting patients...");
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    @Override
    public String toString() {
        return super.toString() + ", PatientsAssigned: " + patientsAssigned;
    }
}