package com.klu;

import java.sql.*;
import java.util.Scanner;

public class CRUD {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/employeeDB";
        String usr = "root";
        String pwd = "RamDixit2005@";

        try {
            Connection con = DriverManager.getConnection(url, usr, pwd);
            System.out.println("✅ Connection Established");

            Statement st = con.createStatement();

            // ===== CREATE TABLES =====
            st.execute(
                "CREATE TABLE IF NOT EXISTS Department(" +
                "deptid INT PRIMARY KEY AUTO_INCREMENT," +
                "deptname VARCHAR(30))"
            );

            st.execute(
                "CREATE TABLE IF NOT EXISTS Employee(" +
                "empid INT PRIMARY KEY AUTO_INCREMENT," +
                "empname VARCHAR(30)," +
                "salary DOUBLE," +
                "deptid INT," +
                "FOREIGN KEY (deptid) REFERENCES Department(deptid))"
            );

            System.out.println("✅ Tables Ready");

            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n===== MENU =====");
                System.out.println("1. Insert Department");
                System.out.println("2. Insert Employee");
                System.out.println("3. View Employees");
                System.out.println("4. Update Employee Salary");
                System.out.println("5. Delete Employee");
                System.out.println("6. Exit");
                System.out.print("Enter Choice: ");
                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.print("Enter Department Name: ");
                        sc.nextLine();
                        String dname = sc.nextLine();

                        PreparedStatement ps1 =
                            con.prepareStatement(
                                "INSERT INTO Department(deptname) VALUES (?)");
                        ps1.setString(1, dname);
                        ps1.executeUpdate();

                        System.out.println("✅ Department Inserted");
                        break;

                    case 2:
                        System.out.print("Enter Employee Name: ");
                        sc.nextLine();
                        String name = sc.nextLine();

                        System.out.print("Enter Salary: ");
                        double sal = sc.nextDouble();

                        System.out.print("Enter Dept ID: ");
                        int did = sc.nextInt();

                        PreparedStatement ps2 =
                            con.prepareStatement(
                                "INSERT INTO Employee(empname,salary,deptid) VALUES (?,?,?)");
                        ps2.setString(1, name);
                        ps2.setDouble(2, sal);
                        ps2.setInt(3, did);

                        ps2.executeUpdate();
                        System.out.println("✅ Employee Inserted");
                        break;

                    case 3:
                        ResultSet rs =
                            st.executeQuery("SELECT * FROM Employee");

                        System.out.println("\nID\tName\tSalary\tDeptID");
                        System.out.println("--------------------------------");
                        while (rs.next()) {
                            System.out.println(
                                rs.getInt("empid") + "\t" +
                                rs.getString("empname") + "\t" +
                                rs.getDouble("salary") + "\t" +
                                rs.getInt("deptid"));
                        }
                        break;

                    case 4:
                        System.out.print("Enter Employee ID: ");
                        int uid = sc.nextInt();

                        System.out.print("Enter New Salary: ");
                        double nsal = sc.nextDouble();

                        PreparedStatement ps3 =
                            con.prepareStatement(
                                "UPDATE Employee SET salary=? WHERE empid=?");
                        ps3.setDouble(1, nsal);
                        ps3.setInt(2, uid);

                        int rows = ps3.executeUpdate();
                        if (rows > 0)
                            System.out.println("✅ Salary Updated");
                        else
                            System.out.println("❌ Employee Not Found");
                        break;

                    case 5:
                        System.out.print("Enter Employee ID to Delete: ");
                        int did2 = sc.nextInt();

                        PreparedStatement ps4 =
                            con.prepareStatement(
                                "DELETE FROM Employee WHERE empid=?");
                        ps4.setInt(1, did2);

                        int del = ps4.executeUpdate();
                        if (del > 0)
                            System.out.println("✅ Employee Deleted");
                        else
                            System.out.println("❌ Employee Not Found");
                        break;

                    case 6:
                        System.out.println("👋 Program Closed");
                        break;

                    default:
                        System.out.println("❌ Invalid Choice");
                }

            } while (choice != 6);

            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
