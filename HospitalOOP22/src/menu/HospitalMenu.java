package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalMenu implements Menu {

    private ArrayList<MedicalStaff> staff = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Nurse");
        System.out.println("3. View Staff");
        System.out.println("4. Make Staff Work");
        System.out.println("0. Exit");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            try {
                displayMenu();
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addDoctor();
                    case 2 -> addNurse();
                    case 3 -> viewStaff();
                    case 4 -> staff.forEach(MedicalStaff::work);
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
    }

    private void addDoctor() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        staff.add(new Doctor(1, name, "General", 5, "Therapy"));
    }

    private void addNurse() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        staff.add(new Nurse(2, name, "General", 3, 5));
    }

    private void viewStaff() {
        staff.forEach(System.out::println);
    }
}
