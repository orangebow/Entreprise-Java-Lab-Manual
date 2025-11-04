package com.example.app;

import com.example.dao.StudentDao;
import com.example.model.Student;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDao studentDao = new StudentDao();
        int choice;

        while (true) {
            System.out.println("\n=== Student Management (CRUD Demo) ===");
            System.out.println("1. Create Student");
            System.out.println("2. Fetch Student by ID");
            System.out.println("3. Fetch All Students");
            System.out.println("4. Update Student Email");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // 1. CREATE Operation
                        System.out.print("Enter First Name: ");
                        String firstName = sc.nextLine();
                        System.out.print("Enter Last Name: ");
                        String lastName = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        Student newStudent = new Student(firstName, lastName, email);
                        studentDao.saveStudent(newStudent);
                        System.out.println("✅ Student saved successfully! ID: " + newStudent.getId());
                        break;

                    case 2:
                        // 2. READ (by ID) Operation
                        System.out.print("Enter Student ID to fetch: ");
                        long idToFetch = sc.nextLong();
                        sc.nextLine(); // Consume newline
                        Student foundStudent = studentDao.getStudentById(idToFetch);
                        if (foundStudent != null) {
                            System.out.println("Student Details: " + foundStudent);
                        } else {
                            System.out.println("⚠️ Student with ID " + idToFetch + " not found.");
                        }
                        break;

                    case 3:
                        // 3. READ (All) Operation
                        System.out.println("\n--- All Students ---");
                        List<Student> students = studentDao.getAllStudents();
                        if (students.isEmpty()) {
                            System.out.println("No students found in the database.");
                        } else {
                            students.forEach(s -> System.out.println(s));
                        }
                        break;

                    case 4:
                        // 4. UPDATE Operation
                        System.out.print("Enter Student ID to update: ");
                        long idToUpdate = sc.nextLong();
                        sc.nextLine(); // Consume newline

                        Student studentToUpdate = studentDao.getStudentById(idToUpdate);
                        if (studentToUpdate != null) {
                            System.out.print("Enter new Email address: ");
                            String newEmail = sc.nextLine();
                            studentToUpdate.setEmail(newEmail);
                            studentDao.updateStudent(studentToUpdate);
                            System.out.println("✅ Student ID " + idToUpdate + " updated successfully!");
                        } else {
                            System.out.println("⚠️ Student with ID " + idToUpdate + " not found. Cannot update.");
                        }
                        break;

                    case 5:
                        // 5. DELETE Operation
                        System.out.print("Enter Student ID to delete: ");
                        long idToDelete = sc.nextLong();
                        sc.nextLine(); // Consume newline

                        studentDao.deleteStudent(idToDelete);
                        System.out.println("✅ Attempted to delete Student with ID " + idToDelete + ".");
                        break;

                    case 6:
                        // 6. Exit
                        System.out.println("Exiting Student Management System. Goodbye!");
                        sc.close();
                        return; // Exit the main method

                    default:
                        System.out.println("❌ Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a valid number for your choice.");
                sc.nextLine(); // Clear the invalid input
            }
        }
    }
}