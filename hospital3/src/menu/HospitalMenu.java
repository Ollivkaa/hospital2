package menu;

import model.*;
import java.util.*;

public  class HospitalMenu implements Menu {

    private final List<MedicalStaff> staff = new ArrayList<>();
    private final List<Patient> patients = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public HospitalMenu() {
        staff.add(new Doctor(1, "Dr. Almas", "Cardiology", 10, "Surgery"));
        staff.add(new Nurse(2, "Aida", "Emergency", 5, 8));
        patients.add(new Patient(1, "Nursultan", 45, "Hypertension"));
        appointments.add(new Appointment(1, "Nursultan", "Dr. Almas", "2026-01-26"));
    }

    @Override
    public void displayMenu() {

    }

    @Override
    public void run() {
        while (true) {
            showMenu();
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> addDoctor();
                    case 2 -> addNurse();
                    case 3 -> staff.forEach(System.out::println);
                    case 4 -> staff.forEach(MedicalStaff::work);
                    case 5 -> showTreatment();
                    case 6 -> viewByType(Doctor.class);
                    case 7 -> viewByType(Nurse.class);
                    case 8 -> addPatient();
                    case 9 -> patients.forEach(System.out::println);
                    case 10 -> addAppointment();
                    case 11 -> appointments.forEach(System.out::println);
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showMenu() {
        System.out.println("""
        1.Add Doctor   2.Add Nurse   3.View Staff
        4.Staff Work   5.Treatment  6.Doctors
        7.Nurses      8.Add Patient
        9.Patients    10.Add Appointment
        11.Appointments   0.Exit
        """);
    }

    private void addDoctor() {
        System.out.print("ID Name Dept Exp Spec: ");
        staff.add(new Doctor(
                sc.nextInt(), sc.next(), sc.next(),
                sc.nextInt(), sc.next()
        ));
        sc.nextLine();
    }

    private void addNurse() {
        System.out.print("ID Name Dept Exp Patients: ");
        staff.add(new Nurse(
                sc.nextInt(), sc.next(), sc.next(),
                sc.nextInt(), sc.nextInt()
        ));
        sc.nextLine();
    }

    private void addPatient() {
        System.out.print("ID Name Age Condition: ");
        patients.add(new Patient(
                sc.nextInt(), sc.next(), sc.nextInt(), sc.next()
        ));
        sc.nextLine();
    }

    private void addAppointment() {
        System.out.print("ID Patient Doctor Date: ");
        appointments.add(new Appointment(
                sc.nextInt(), sc.next(), sc.next(), sc.next()
        ));
        sc.nextLine();
    }

    private void viewByType(Class<?> type) {
        staff.stream()
                .filter(type::isInstance)
                .forEach(System.out::println);
    }

    private void showTreatment() {
        for (MedicalStaff s : staff) {
            if (s instanceof Treatable t) {
                t.treat("Patient");
            }
        }
    }
}

