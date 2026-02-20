package com.klu;

import java.sql.*;
import java.util.Scanner;

public class JDBCCreate {

    static String url = "jdbc:mysql://localhost:3306/fsaddb";
    static String user = "root";
    static String pwd = "root";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection(url, user, pwd);
            System.out.println("✔ Database Connected");

            // 🔥 CREATE TABLES FIRST
            createTables(con);

            int choice;

            do {
                System.out.println("\n========= MENU =========");
                System.out.println("1. Insert Department");
                System.out.println("2. Insert Employee");
                System.out.println("3. Display Departments");
                System.out.println("4. Display Employees");
                System.out.println("5. Update Employee Salary");
                System.out.println("6. Delete Employee");
                System.out.println("7. Exit");
                System.out.print("Enter Choice: ");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        insertDepartment(con);
                        break;
                    case 2:
                        insertEmployee(con);
                        break;
                    case 3:
                        displayDepartments(con);
                        break;
                    case 4:
                        displayEmployees(con);
                        break;
                    case 5:
                        updateSalary(con);
                        break;
                    case 6:
                        deleteEmployee(con);
                        break;
                    case 7:
                        System.out.println("✔ Exiting Program");
                        break;
                    default:
                        System.out.println("❌ Invalid Choice");
                }

            } while (choice != 7);

            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= CREATE TABLES =================
    static void createTables(Connection con) throws Exception {

        Statement st = con.createStatement();

        String deptTable =
                "CREATE TABLE IF NOT EXISTS Department (" +
                "dept_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "dept_name VARCHAR(30), " +
                "location VARCHAR(30))";

        String empTable =
                "CREATE TABLE IF NOT EXISTS Employee (" +
                "emp_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "emp_name VARCHAR(30), " +
                "age INT, " +
                "salary DOUBLE, " +
                "designation VARCHAR(30), " +
                "dept_id INT)";

        st.execute(deptTable);
        st.execute(empTable);

        System.out.println("✔ Tables Checked/Created");
    }

    // ================= INSERT DEPARTMENT =================
    static void insertDepartment(Connection con) throws Exception {

        sc.nextLine();
        System.out.print("Enter Department Name: ");
        String dname = sc.nextLine();

        System.out.print("Enter Department Location: ");
        String loc = sc.nextLine();

        PreparedStatement ps =
                con.prepareStatement(
                        "INSERT INTO Department(dept_name, location) VALUES (?,?)");

        ps.setString(1, dname);
        ps.setString(2, loc);
        ps.executeUpdate();

        System.out.println("✔ Department Inserted Successfully");
    }

    // ================= INSERT EMPLOYEE =================
    static void insertEmployee(Connection con) throws Exception {

        sc.nextLine();
        System.out.print("Enter Employee Name: ");
        String ename = sc.nextLine();

        System.out.print("Enter Employee Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Employee Salary: ");
        double sal = sc.nextDouble();

        sc.nextLine();
        System.out.print("Enter Employee Designation: ");
        String desig = sc.nextLine();

        System.out.print("Enter Department ID: ");
        int did = sc.nextInt();

        PreparedStatement ps =
                con.prepareStatement(
                        "INSERT INTO Employee(emp_name, age, salary, designation, dept_id) VALUES (?,?,?,?,?)");

        ps.setString(1, ename);
        ps.setInt(2, age);
        ps.setDouble(3, sal);
        ps.setString(4, desig);
        ps.setInt(5, did);
        ps.executeUpdate();

        System.out.println("✔ Employee Inserted Successfully");
    }

    // ================= DISPLAY DEPARTMENTS =================
    static void displayDepartments(Connection con) throws Exception {

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Department");

        System.out.println("\nDeptID | Dept Name | Location");
        System.out.println("------------------------------");

        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " | " +
                    rs.getString(2) + " | " +
                    rs.getString(3));
        }
    }

    // ================= DISPLAY EMPLOYEES =================
    static void displayEmployees(Connection con) throws Exception {

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Employee");

        System.out.println("\nEmpID | Name | Age | Salary | Designation | DeptID");
        System.out.println("--------------------------------------------------");

        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " | " +
                    rs.getString(2) + " | " +
                    rs.getInt(3) + " | " +
                    rs.getDouble(4) + " | " +
                    rs.getString(5) + " | " +
                    rs.getInt(6));
        }
    }

    // ================= UPDATE SALARY =================
    static void updateSalary(Connection con) throws Exception {

        System.out.print("Enter Employee ID: ");
        int eid = sc.nextInt();

        System.out.print("Enter New Salary: ");
        double nsal = sc.nextDouble();

        PreparedStatement ps =
                con.prepareStatement(
                        "UPDATE Employee SET salary=? WHERE emp_id=?");

        ps.setDouble(1, nsal);
        ps.setInt(2, eid);
        ps.executeUpdate();

        System.out.println("✔ Salary Updated");
    }

    // ================= DELETE EMPLOYEE =================
    static void deleteEmployee(Connection con) throws Exception {

        System.out.print("Enter Employee ID to Delete: ");
        int eid = sc.nextInt();

        PreparedStatement ps =
                con.prepareStatement(
                        "DELETE FROM Employee WHERE emp_id=?");

        ps.setInt(1, eid);
        ps.executeUpdate();

        System.out.println("✔ Employee Deleted");
    }
}
