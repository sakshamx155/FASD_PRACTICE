package com.klu.app;
import com.klu.model.Department;
import com.klu.model.Employee;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.klu.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class MainApp {

    static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
    	
    	Session session = factory.openSession();
    	Transaction tx = session.beginTransaction();
    	Scanner sc = new Scanner(System.in);
    int choice;
    do {
            System.out.println(".....Main Menu.....");
            System.out.println("1. Insert Employee");
            System.out.println("2. Display Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.println("Select Your Choice : ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertEmployee(sc);
                    break;
                case 2:
                    displayEmployee(sc);
                    break;
                case 3:
                    updateEmployee(sc);
                    break;
                case 4:
                    deleteEmployee(sc);
                    break;
                case 5:
                    System.out.println("Thank you");
                    break;
                default:
                    System.out.println("Invalid choice !");
            }

        } while (choice != 5);

        factory.close();
        sc.close();
    }

    static void insertEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Department Name : ");
        String deptName = sc.next();

        Department dept = new Department();
        dept.setDeptName(deptName);

        System.out.print("Enter Employee Name : ");
        String empName = sc.next();

        System.out.print("Enter Employee Salary : ");
        double salary = sc.nextDouble();

        Employee emp = new Employee();
        emp.setEmpName(empName);
        emp.setSalary(salary);
        emp.setDepartment(dept);

        session.persist(emp);
        session.persist(dept);
        
        tx.commit();
        session.close();

        System.out.println("Employee data inserted Successfully");
        }

    static void displayEmployee(Scanner sc) {

        Session session = factory.openSession();

        System.out.print("Enter Employee Id : ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            System.out.println("Employee Name : " + emp.getEmpName());
            System.out.println("Salary : " + emp.getSalary());
            System.out.println("Department : " + emp.getDepartment().getDeptName());
        } else {
            System.out.println("Employee not found");
        }
        session.close();
    }
    static void updateEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee Id to Update : ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);
        if (emp != null) {

//            System.out.print("Enter New Name : ");
//            emp.setEmpName(sc.next());

            System.out.print("Enter New Salary : ");
            emp.setSalary(sc.nextDouble());

//            session.update(emp);
            tx.commit();

            System.out.println("Salary Updated!");
        } else {
            System.out.println("Employee not found");
            tx.rollback();
        }

        session.close();
    }

    static void deleteEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee Id to Delete : ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            session.delete(emp);
            tx.commit();
            System.out.println("Employee deleted!");
        } else {
            System.out.println("Employee not found");
        }

        session.close();
    }

}