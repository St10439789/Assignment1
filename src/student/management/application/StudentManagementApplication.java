/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package student.management.application;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;
/**
 *
 * @author RC_Student_lab
 */
public class StudentManagementApplication {

    /**
     * @param args the command line arguments
     */
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
       
   

        while (true) {
            int choice = displayMenu();
            if (choice == 1) {
                captureStudent();
            } else if (choice == 2) {
                searchStudent();
            } else if (choice == 3) {
                deleteStudent();
            } else if (choice == 4) {
                printReport();
            } else if (choice == 5) {
                System.out.println("Exiting Application.");
                break;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    private static int displayMenu() {
        System.out.println("STUDENT MANAGEMENT APPLICATION");
        System.out.println("**************************************");
        System.out.println("(1) Capture a new student.");
        System.out.println("(2) Search for a student.");
        System.out.println("(3) Delete a student.");
        System.out.println("(4) Print student report.");
        System.out.println("(5) Exit Application.");
        System.out.print("Enter your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    //capturing the student
    private static void captureStudent() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        int age = getValidStudentAge(scanner);
        System.out.print("Enter student email: ");
        String email = scanner.nextLine();
        System.out.print("Enter student course: ");
        String course = scanner.nextLine();

        Student student = new Student(studentId, name, age, email, course);
        studentList.add(student);
        System.out.println("Student captured successfully.");
    }

    private static void searchStudent() {
        System.out.print("Enter student ID to search: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student foundStudent = null;

        for (Student student : studentList) {
            if (student.getStudentId() == studentId) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        Student foundStudent = null;

        for (Student student : studentList) {
            if (student.getStudentId() == studentId) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent != null) {
            studentList.remove(foundStudent);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void printReport() {
        if (studentList.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("Student Report:");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    private static int getValidStudentAge(Scanner scanner) {
        int age = -1; // Initialize with an invalid age

        while (true) {
            System.out.print("Enter the student age: ");
            String input = scanner.nextLine();

            if (isValidInteger(input)) {
                age = Integer.parseInt(input);

                if (age >= 16) {
                    return age; // Return valid age and exit method
                } else {
                    System.out.println("You have entered an incorrect student age!!! Please re-enter the student age.");
                }
            } else {
                System.out.println("You have entered an incorrect student age!!! Please re-enter the student age.");
            }
        }
    }

    private static boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Inner class Student
    static class Student {
        private int studentId;
        private String name;
        private int age;
        private String email;
        private String course;

        // Constructor
        public Student(int studentId, String name, int age, String email, String course) {
            this.studentId = studentId;
            this.name = name;
            this.age = age;
            this.email = email;
            this.course = course;
        }

        // Getters
        public int getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getEmail() {
            return email;
        }

        public String getCourse() {
            return course;
        }

        // toString method
        @Override
        public String toString() {
            return "ID: " + studentId + ", Name: " + name + ", Age: " + age +
                   ", Email: " + email + ", Course: " + course;
        }
    }

}



