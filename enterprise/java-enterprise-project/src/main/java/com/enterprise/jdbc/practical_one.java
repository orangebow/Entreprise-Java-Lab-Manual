package com.enterprise.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class practical_one {
	public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/enterprise";
        String username = "anand";
        String password = "java";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Load and register the driver
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL Driver registered successfully.");

            // 2. Establish connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to PostgreSQL successfully! 🐘");

            // 3. Create a statement
            stmt = conn.createStatement();
            
            // 4. Insert data into students Table
            String studentName = "Anand";
            String studentEmail = "Ananda@example.com";
            int studentAge = 21;
            
            String insertSQL = "INSERT INTO students(name, email, age) VALUES ('" + studentName + "', '" + studentEmail + "', " + studentAge + ")";
            int rowsInserted = stmt.executeUpdate(insertSQL);
            System.out.println(rowsInserted + " row(s) inserted.");

            // 5. Retrieve Data from students Table
            String selectSQL = "SELECT id, name, email, age FROM students";
            rs = stmt.executeQuery(selectSQL);

            // 6. Process the result
            System.out.println("\n----------- Student Records -----------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");

                System.out.println("ID: " + id + " | Name: " + name + " | Email: " + email + " | Age: " + age);
            }
            System.out.println("-------------------------------------");

        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 7. Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("Resources closed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
