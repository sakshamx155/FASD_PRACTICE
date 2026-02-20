package com.klu;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class MainApp {

 public static void main(String[] args) {
  //load Configuration & create the SessionFactory
  SessionFactory factory=new Configuration().configure().buildSessionFactory();
  //Open Session
  Session session=factory.openSession();
  //Begin Transacation
  Transaction tx=session.beginTransaction();
  //create the table/obect
  Student s=new Student("Ravi");
  //save the data/object
  session.save(s);
  //commit
  tx.commit();
  //close
  session.close();
  factory.close();
  System.out.println("Student data inserted Successfully");
    
        //open session
  
 }

}