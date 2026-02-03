package model;

public class Appointment {
    private int appointmentId;
    private String patientName;
    private String doctorName;
    private String date;
    private String status;

    public Appointment(int appointmentId, String patientName, String doctorName, String date) {
        setAppointmentId(appointmentId);
        setPatientName(patientName);
        setDoctorName(doctorName);
        setDate(date);
        this.status = "Scheduled";
    }

    // Getters
    public int getAppointmentId() {
        return appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    // Setters with validation
    public void setAppointmentId(int appointmentId) {
        if (appointmentId <= 0)
            throw new IllegalArgumentException("Appointment ID must be positive");
        this.appointmentId = appointmentId;
    }

    public void setPatientName(String patientName) {
        if (patientName == null || patientName.trim().isEmpty())
            throw new IllegalArgumentException("Patient name cannot be empty");
        this.patientName = patientName;
    }

    public void setDoctorName(String doctorName) {
        if (doctorName == null || doctorName.trim().isEmpty())
            throw new IllegalArgumentException("Doctor name cannot be empty");
        this.doctorName = doctorName;
    }

    public void setDate(String date) {
        if (date == null || date.trim().isEmpty())
            throw new IllegalArgumentException("Date cannot be empty");
        this.date = date;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty())
            throw new IllegalArgumentException("Status cannot be empty");
        this.status = status;
    }

    // Additional methods
    public void complete() {
        this.status = "Completed";
    }

    public void cancel() {
        this.status = "Cancelled";
    }

    public void reschedule(String newDate) {
        setDate(newDate);
        this.status = "Rescheduled";
    }

    public boolean isActive() {
        return status.equals("Scheduled") || status.equals("Rescheduled");
    }

    @Override
    public String toString() {
        return "Appointment{ID: " + appointmentId + ", Patient: " + patientName +
                ", Doctor: " + doctorName + ", Date: " + date + ", Status: " + status + "}";
    }
}

