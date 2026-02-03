package model;

public abstract class MedicalStaff {
    protected int staffId; // 0 = not saved yet (DB will generate)
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

    public int getStaffId() { return staffId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getExperienceYears() { return experienceYears; }

    public void setStaffId(int staffId) {
        if (staffId < 0)
            throw new IllegalArgumentException("Staff ID cannot be negative");
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

    @Override
    public String toString() {
        String idText = (staffId == 0) ? "new" : String.valueOf(staffId);
        return "[" + getRole() + "] " + name + " (ID: " + idText +
                ", Department: " + department + ", Experience: " + experienceYears + " years)";
    }
}