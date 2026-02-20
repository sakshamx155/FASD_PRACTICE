package com.example;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.util.HibernateUtil;

public class MainApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int ch;

        do {
            System.out.println("\n====== HIBERNATE HQL MENU ======");
            System.out.println("1. Insert Sample Data");
            System.out.println("2. Show All Employees");
            System.out.println("3. Update Salary (HQL)");
            System.out.println("4. Delete Employee (HQL)");
            System.out.println("5. Sort Employees by Salary");
            System.out.println("6. Aggregate Functions");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    insertData();
                    break;
                case 2:
                    showEmployees();
                    break;
                case 3:
                    updateSalary();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    sortEmployees();
                    break;
                case 6:
                    aggregateFunctions();
                    break;
                case 0:
                    System.out.println("Program Ended");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        } while (ch != 0);
    }

    // 1️⃣ INSERT DATA
    static void insertData() {

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        Department d1 = new Department("HR");
        Department d2 = new Department("IT");

        s.persist(d1);
        s.persist(d2);

        s.persist(new Employee("Ravi", 30000, d1));
        s.persist(new Employee("Om", 50000, d2));
        s.persist(new Employee("Amit", 45000, d2));

        tx.commit();
        s.close();

        System.out.println("Sample data inserted successfully");
    }

    // 2️⃣ SELECT ALL
    static void showEmployees() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        Query<Employee> q = s.createQuery("from Employee", Employee.class);

        System.out.println("\nEMPID | NAME | SALARY | DEPARTMENT");

        for (Employee e : q.list()) {
            System.out.println(
                e.getEmpId() + " | " +
                e.getName() + " | " +
                e.getSalary() + " | " +
                e.getDept().getDeptName()
            );
        }

        s.close();
    }

    // 3️⃣ UPDATE (HQL)
    static void updateSalary() {

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        System.out.print("Enter New Salary: ");
        double sal = sc.nextDouble();

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        Query q = s.createQuery(
            "update Employee set salary = :sal where empId = :id"
        );
        q.setParameter("sal", sal);
        q.setParameter("id", id);

        int res = q.executeUpdate();

        tx.commit();
        s.close();

        System.out.println(res + " record updated");
    }

    // 4️⃣ DELETE (HQL)
    static void deleteEmployee() {

        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        Query q = s.createQuery(
            "delete from Employee where empId = :id"
        );
        q.setParameter("id", id);

        int res = q.executeUpdate();

        tx.commit();
        s.close();

        System.out.println(res + " record deleted");
    }

    // 5️⃣ SORTING
    static void sortEmployees() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        Query<Employee> q = s.createQuery(
            "from Employee order by salary desc", Employee.class
        );

        System.out.println("\n--- Employees Sorted by Salary (DESC) ---");

        for (Employee e : q.list()) {
            System.out.println(e.getName() + " -> " + e.getSalary());
        }

        s.close();
    }

    // 6️⃣ AGGREGATE FUNCTIONS
    static void aggregateFunctions() {

        Session s = HibernateUtil.getSessionFactory().openSession();

        Query countQ = s.createQuery("select count(*) from Employee");
        Query avgQ   = s.createQuery("select avg(salary) from Employee");
        Query sumQ   = s.createQuery("select sum(salary) from Employee");

        System.out.println("\nTotal Employees : " + countQ.uniqueResult());
        System.out.println("Average Salary  : " + avgQ.uniqueResult());
        System.out.println("Total Salary    : " + sumQ.uniqueResult());

        s.close();
    }
}
