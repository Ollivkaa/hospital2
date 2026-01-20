package model;

public class Doctor extends MedicalStaff {
    private String specialization;

    public Doctor(int id, String name, String dept, int exp, String spec) {
        super(id, name, dept, exp);
        setSpecialization(spec);
    }

    @Override
    public void work() {
        System.out.println("Doctor " + name + " is treating patients");
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isEmpty())
            throw new IllegalArgumentException("Specialization required");
        this.specialization = specialization;
    }
}
