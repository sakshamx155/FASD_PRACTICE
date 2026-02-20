package com.klu.model;
import javax.persistence.*;

@Entity
@Table(name = "emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empid;
    private String empname;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "deptid")
    private Department department;
    public void setEmpId(int empid) {
        this.empid = empid;
    }
    public int getEmpId() {
        return empid;
    }
    public void setEmpName(String empname) {
        this.empname = empname;
    }
    public String getEmpName() {
        return empname;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public double getSalary() {
        return salary;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Department getDepartment() {
        return department;
    }
}