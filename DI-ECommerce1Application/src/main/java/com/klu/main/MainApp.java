package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.klu.config.AppConfig;
import com.klu.model.ProductOrder;

public class MainApp {

  public static void main(String[] args) {
    
    ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
    ProductOrder p= (ProductOrder)context.getBean("order"); // return the object here so casting required if no casting ProductOrder p= (Object)context.getBean("order");
    p.display();
    /*
    ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
    ProductOrder p= (ProductOrder)context.getBean("order"); // return the object here so casting required if no casting ProductOrder p= (Object)context.getBean("order");
    p.display();
    */
    ApplicationContext context1 = new AnnotationConfigApplicationContext(AppConfig.class);
    ProductOrder p1 = context1.getBean(ProductOrder.class); // returns the Class here so no casting required
    
    p1.display();
    
  }

} 