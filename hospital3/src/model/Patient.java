package model;

public class Patient {
    private int patientId;
    private String name;
    private int age;
    private String condition;

    public Patient(int patientId, String name, int age, String condition) {
        setPatientId(patientId);
        setName(name);
        setAge(age);
        setCondition(condition);
    }

    // Getters
    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCondition() {
        return condition;
    }

    // Setters with validation
    public void setPatientId(int patientId) {
        if (patientId <= 0)
            throw new IllegalArgumentException("Patient ID must be positive");
        this.patientId = patientId;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0 || age > 150)
            throw new IllegalArgumentException("Age must be between 0 and 150");
        this.age = age;
    }

    public void setCondition(String condition) {
        if (condition == null || condition.trim().isEmpty())
            throw new IllegalArgumentException("Condition cannot be empty");
        this.condition = condition;
    }

    // Additional methods
    public boolean isMinor() {
        return age < 18;
    }

    public String getAgeCategory() {
        if (age < 18) return "Minor";
        if (age < 60) return "Adult";
        return "Senior";
    }

    @Override
    public String toString() {
        return "Patient{ID: " + patientId + ", Name: " + name +
                ", Age: " + age + ", Condition: " + condition + "}";
    }
}