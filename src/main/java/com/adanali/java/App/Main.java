package com.adanali.java.App;

import com.adanali.java.dao.JDBCStudentDao;
import com.adanali.java.dao.StudentDao;
import com.adanali.java.domain.Student;
import com.adanali.java.service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDao dao = new JDBCStudentDao();
    private static final StudentService studentService = new StudentService(dao);

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
        String name = readString("Enter the name : ");
        String email = readString("Enter the email : ");
        String gender = readString("Enter the gender : ");
        studentService.createStudent(new Student(name,email,gender));
    }

    private static void readStudents() {
        int choice;

        do {
            System.out.println("""
            ----- Read Student -----
            1. Read by ID
            2. Read all
            0. Back
        """);

            choice = readInt("Choose option: ");

            switch (choice) {
                case 1 -> readStudentById();
                case 2 -> readAllStudents();
                case 0 -> {}
                default -> System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }

    private static void readStudentById(){
        int id = readInt("Enter student ID: ");
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()){
            printStudent(student.get());
        }else {
            IO.println("Student not found...");
        }
    }

    private static void readAllStudents(){
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()){
            IO.println("No Students found...");
        }else students.forEach(Main::printStudent);
    }

    private static void updateStudent() {
        int choice;

        do {
            System.out.println("""
            1. Update name
            2. Update email
            3. Update gender
            0. Back
        """);

            choice = readInt("Choose option: ");

            switch (choice) {
                case 1 -> updateName();
                case 2 -> updateEmail();
                case 3 -> updateGender();
                case 0 -> {}
                default -> System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    private static void updateName() {
        int id = readInt("Student ID: ");
        String name = readString("New name: ");
        studentService.updateStudentName(id, name);
    }

    private static void updateEmail() {
        int id = readInt("Student ID: ");
        String email = readString("New Email: ");
        studentService.updateStudentEmail(id, email);
    }

    private static void updateGender() {
        int id = readInt("Student ID: ");
        String gender = readString("New Gender: ");
        studentService.updateStudentGender(id, gender);
    }

    private static void deleteStudent() {
        int id = readInt("Student ID: ");
        studentService.deleteStudent(id);
    }

    private static void printStudent(Student student) {
        System.out.printf("""
        -------------------------
        ID     : %d
        Name   : %s
        Email  : %s
        Gender : %s
        -------------------------
        """, student.getId(), student.getName(), student.getEmail(), student.getGender()
        );
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message + " : ");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static String readString(String message) {
        System.out.print(message + " : ");
        return scanner.nextLine().trim();
    }
}
