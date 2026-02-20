package com.klu;

import java.sql.*;
import java.util.Scanner;

public class CRUD_Operation {

    static String url = "jdbc:mysql://localhost:3306/testdb";
    static String user = "root";
    static String password = "Ashu@2005";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        createTables();
        int choice;

        do {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Insert Department");
            System.out.println("2. Insert Employee");
            System.out.println("3. View Employees");
            System.out.println("4. Update Employee Salary");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // ✅ buffer clear

            switch (choice) {
                case 1: insertDept(); break;
                case 2: insertEmployee(); break;
                case 3: viewEmployees(); break;
                case 4: updateEmployee(); break;
                case 5: deleteEmployee(); break;
                case 6: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }

    // ================= TABLE CREATION =================
    static void createTables() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {

            st.execute(
                "CREATE TABLE IF NOT EXISTS dept (" +
                "dept_id INT PRIMARY KEY, dept_name VARCHAR(50))");

            st.execute(
                "CREATE TABLE IF NOT EXISTS employee (" +
                "emp_id BIGINT PRIMARY KEY, emp_name VARCHAR(50), salary DOUBLE, dept_id INT, " +
                "FOREIGN KEY (dept_id) REFERENCES dept(dept_id))");

            System.out.println("Tables created or already exist.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= INSERT DEPARTMENT =================
    static void insertDept() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO dept VALUES (?, ?)");

            System.out.print("Enter Dept ID: ");
            int deptId = sc.nextInt();
            sc.nextLine(); // ✅ buffer clear

            System.out.print("Enter Dept Name: ");
            String deptName = sc.nextLine(); // ✅ allows spaces

            ps.setInt(1, deptId);
            ps.setString(2, deptName);

            ps.executeUpdate();
            System.out.println("Department inserted successfully.");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Dept ID already exists!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= CHECK DEPT EXISTS =================
    static boolean deptExists(int deptId) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            PreparedStatement ps =
                    con.prepareStatement("SELECT dept_id FROM dept WHERE dept_id=?");
            ps.setInt(1, deptId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= INSERT EMPLOYEE =================
    static void insertEmployee() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            System.out.print("Enter Emp ID: ");
            long empId = sc.nextLong();
            sc.nextLine(); // ✅ buffer clear

            System.out.print("Enter Emp Name: ");
            String empName = sc.nextLine(); // ✅ full name supported

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            System.out.print("Enter Dept ID: ");
            int deptId = sc.nextInt();
            sc.nextLine();

            if (!deptExists(deptId)) {
                System.out.println("❌ Dept ID does not exist. Insert department first!");
                return;
            }

            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO employee VALUES (?, ?, ?, ?)");

            ps.setLong(1, empId);
            ps.setString(2, empName);
            ps.setDouble(3, salary);
            ps.setInt(4, deptId);

            ps.executeUpdate();
            System.out.println("Employee inserted successfully.");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Employee ID already exists!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= VIEW EMPLOYEES =================
    static void viewEmployees() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT * FROM employee");

            System.out.println("\nEmpID     Name        Salary   DeptID");
            while (rs.next()) {
                System.out.println(
                        rs.getLong("emp_id") + "   " +
                        rs.getString("emp_name") + "   " +
                        rs.getDouble("salary") + "   " +
                        rs.getInt("dept_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE EMPLOYEE =================
    static void updateEmployee() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            PreparedStatement ps =
                    con.prepareStatement("UPDATE employee SET salary=? WHERE emp_id=?");

            System.out.print("Enter Emp ID: ");
            long empId = sc.nextLong();

            System.out.print("Enter New Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            ps.setDouble(1, salary);
            ps.setLong(2, empId);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Salary updated." : "Employee not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE EMPLOYEE =================
    static void deleteEmployee() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM employee WHERE emp_id=?");

            System.out.print("Enter Emp ID to delete: ");
            long empId = sc.nextLong();
            sc.nextLine();

            ps.setLong(1, empId);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Employee deleted." : "Employee not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
