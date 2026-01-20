package model;

public abstract class MedicalStaff {
    protected int staffId;
    protected String name;
    protected String department;
    protected int experienceYears;

    public MedicalStaff(int staffId, String name, String department, int experienceYears) {
        setStaffId(staffId);
        setName(name);
        setDepartment(department);
        setExperienceYears(experienceYears);
    }

    public abstract void work();
    public abstract String getRole();

    public void setStaffId(int staffId) {
        if (staffId <= 0)
            throw new IllegalArgumentException("Staff ID must be positive");
        this.staffId = staffId;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    public void setDepartment(String department) {
        if (department == null || department.isEmpty())
            throw new IllegalArgumentException("Department cannot be empty");
        this.department = department;
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0)
            throw new IllegalArgumentException("Experience cannot be negative");
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name + " (" + department + ")";
    }
}
