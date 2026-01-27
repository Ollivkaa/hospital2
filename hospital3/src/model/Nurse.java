package model;

public class Nurse extends MedicalStaff implements Treatable {
    private int patientsAssigned;

    public Nurse(int id, String name, String dept, int exp, int patients) {
        super(id, name, dept, exp);
        setPatientsAssigned(patients);
    }

    // Implementing abstract methods from MedicalStaff
    @Override
    public void work() {
        System.out.println("Nurse " + name + " is caring for " + patientsAssigned + " patients in " + department);
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    // Implementing Treatable interface
    @Override
    public void treat(String patientName) {
        System.out.println("Nurse " + name + " is providing care to patient: " + patientName);
    }

    @Override
    public String getTreatmentType() {
        return "Nursing Care & Patient Monitoring";
    }

    // Nurse-specific methods
    public void administerMedication(String medication) {
        System.out.println("Nurse " + name + " is administering " + medication);
    }

    public boolean isOverloaded() {
        return patientsAssigned > 10;
    }

    // Getter and setter
    public int getPatientsAssigned() {
        return patientsAssigned;
    }

    public void setPatientsAssigned(int patientsAssigned) {
        if (patientsAssigned < 0)
            throw new IllegalArgumentException("Patients assigned cannot be negative");
        this.patientsAssigned = patientsAssigned;
    }

    @Override
    public String toString() {
        return super.toString() + " | Patients Assigned: " + patientsAssigned;
    }
}