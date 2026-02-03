package menu;

import database.HospitalDAO;
import model.*;

import java.util.Scanner;

public class HospitalMenu implements Menu {
    private final HospitalDAO dao = new HospitalDAO();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("""
        
        1. Добавить Доктора   2. Добавить Медсестру  3. Показать весь персонал
        4. Обновить данные    5. Удалить сотрудника  6. Поиск по имени
        0. Выход
        """);
    }

    @Override
    public void run() {
        while (true) {
            displayMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> addDoctor();
                case "2" -> addNurse();
                case "3" -> dao.getAllStaff().forEach(System.out::println);
                case "4" -> updateStaff();
                case "5" -> deleteStaff();
                case "6" -> searchByName();
                case "0" -> System.exit(0);
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private void addDoctor() {
        System.out.print("Имя: ");
        String name = sc.nextLine();

        System.out.print("Департамент: ");
        String dept = sc.nextLine();

        System.out.print("Опыт (число): ");
        int exp = Integer.parseInt(sc.nextLine());

        System.out.print("Специализация: ");
        String spec = sc.nextLine();

        Doctor d = new Doctor(0, name, dept, exp, spec);
        dao.insertStaff(d);
        System.out.println("Добавлено! Новый ID = " + d.getStaffId());
    }

    private void addNurse() {
        System.out.print("Имя: ");
        String name = sc.nextLine();

        System.out.print("Департамент: ");
        String dept = sc.nextLine();

        System.out.print("Опыт (число): ");
        int exp = Integer.parseInt(sc.nextLine());

        System.out.print("Пациенты (число): ");
        int patients = Integer.parseInt(sc.nextLine());

        Nurse n = new Nurse(0, name, dept, exp, patients);
        dao.insertStaff(n);
        System.out.println("Добавлено! Новый ID = " + n.getStaffId());
    }

    private void deleteStaff() {
        System.out.print("Введите ID для удаления: ");
        int id = Integer.parseInt(sc.nextLine());

        MedicalStaff staff = dao.getStaffById(id);
        if (staff == null) {
            System.out.println("Сотрудник не найден.");
            return;
        }

        System.out.println("Удалить: " + staff + " ? (yes/no)");
        if (sc.nextLine().trim().equalsIgnoreCase("yes")) {
            dao.deleteStaff(id);
            System.out.println("Удалено.");
        } else {
            System.out.println("Отмена.");
        }
    }

    private void updateStaff() {
        System.out.print("Введите ID сотрудника: ");
        int id = Integer.parseInt(sc.nextLine());

        MedicalStaff staff = dao.getStaffById(id);
        if (staff == null) {
            System.out.println("Сотрудник не найден.");
            return;
        }

        System.out.println("Текущие данные: " + staff);

        System.out.print("Новое имя: ");
        String name = sc.nextLine();

        System.out.print("Новый отдел: ");
        String dept = sc.nextLine();

        dao.updateStaff(id, name, dept);
        System.out.println("Данные обновлены.");
    }

    private void searchByName() {
        System.out.print("Введите часть имени: ");
        String name = sc.nextLine();

        dao.searchByName(name).forEach(System.out::println);
    }
}