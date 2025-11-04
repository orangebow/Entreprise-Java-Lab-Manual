package com.enterprise.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStatementDemo {

    // --- IMPORTANT: Change these to your PostgreSQL database details! ---
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/enterprise";
    private static final String USERNAME = "anand";
    private static final String PASSWORD = "java";

    public static void main(String[] args) {
        // Use try-with-resources to automatically close the connection and scanner
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to PostgreSQL database successfully! 🐘");

            // Create the students table if it doesn't already exist
            createTableIfNotExists(connection);

            while (true) {
                System.out.println("\n----------- MENU -----------");
                System.out.println("1. Insert New Student");
                System.out.println("2. View All Students");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the leftover newline character

                switch (choice) {
                    case 1:
                        insertStudent(connection, scanner);
                        break;
                    case 2:
                        queryStudents(connection);
                        break;
                    case 3:
                        System.out.println("Exiting program...");
                        return; // Exit the main method
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection error!");
            e.printStackTrace();
        }
    }

    public static void createTableIfNotExists(Connection connection) throws SQLException {
        // This SQL creates the 'students' table with the correct columns
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                + "id SERIAL PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "email VARCHAR(100) UNIQUE NOT NULL, "
                + "age INT"
                + ");";
        
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'students' is ready.");
        }
    }

    public static void insertStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("\n--- Enter New Student Details ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String insertSQL = "INSERT INTO students(name, email, age) VALUES (?, ?, ?);";

        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, age);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully! 👍");
            }
        }
    }

    public static void queryStudents(Connection connection) throws SQLException {
        System.out.println("\n----------- All Student Records -----------");
        String querySQL = "SELECT id, name, email, age FROM students ORDER BY id;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(querySQL)) {

            boolean found = false;
            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                System.out.printf("ID: %-4d | Name: %-20s | Email: %-25s | Age: %d%n", id, name, email, age);
            }
            if (!found) {
                System.out.println("No students found in the database.");
            }
        }
        System.out.println("-------------------------------------------");
    }
}