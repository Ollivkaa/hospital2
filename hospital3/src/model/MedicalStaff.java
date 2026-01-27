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

    // Abstract methods - must be implemented by children
    public abstract void work();
    public abstract String getRole();

    // Getters
    public int getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    // Setters with exception handling
    public void setStaffId(int staffId) {
        if (staffId <= 0)
            throw new IllegalArgumentException("Staff ID must be positive");
        this.staffId = staffId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty())
            throw new IllegalArgumentException("Department cannot be empty");
        this.department = department;
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0)
            throw new IllegalArgumentException("Experience cannot be negative");
        this.experienceYears = experienceYears;
    }

    // Concrete method
    public void displayInfo() {
        System.out.println(name + " - " + department + " (" + experienceYears + " years)");
    }

    public boolean isExperienced() {
        return experienceYears >= 5;
    }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name + " (ID: " + staffId +
                ", Department: " + department + ", Experience: " + experienceYears + " years)";
    }
}