package model;

public class Doctor extends MedicalStaff implements Treatable {
    private String specialization;

    public Doctor(int id, String name, String dept, int exp, String spec) {
        super(id, name, dept, exp);
        setSpecialization(spec);
    }

    // Implementing abstract methods from MedicalStaff
    @Override
    public void work() {
        System.out.println("Doctor " + name + " is diagnosing and treating patients in " + specialization);
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    // Implementing Treatable interface
    @Override
    public void treat(String patientName) {
        System.out.println("Dr. " + name + " (" + specialization + ") is treating patient: " + patientName);
    }

    @Override
    public String getTreatmentType() {
        return "Medical Treatment - " + specialization;
    }

    // Doctor-specific methods
    public void performSurgery(String surgeryType) {
        System.out.println("Dr. " + name + " is performing " + surgeryType + " surgery");
    }

    public boolean canPerformSurgery() {
        return experienceYears >= 7;
    }

    // Getter and setter
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty())
            throw new IllegalArgumentException("Specialization cannot be empty");
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return super.toString() + " | Specialization: " + specialization;
    }
}
