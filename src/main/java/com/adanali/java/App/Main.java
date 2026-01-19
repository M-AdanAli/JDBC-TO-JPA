package com.adanali.java.App;

import com.adanali.java.service.StudentService;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> createStudent();
                case 2 -> readStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting application...");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                
                ===== Student Management =====
                1. Create student
                2. View students
                3. Update student
                4. Delete student
                0. Exit
                """);
    }

    private static void createStudent() {
        System.out.println("Create Student");
    }

    private static void readStudents() {
        System.out.println("View Students");
    }

    private static void updateStudent() {
        System.out.println("Update Student");
    }

    private static void deleteStudent() {
        System.out.println("Delete Student");
    }

    private static int readInt(String message) {
        System.out.print(message+" : ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Enter a valid number: ");
        }
        return scanner.nextInt();
    }

    private static String readString(String message) {
        System.out.print(message+" : ");
        scanner.nextLine(); // clear buffer
        return scanner.nextLine().trim();
    }
}
