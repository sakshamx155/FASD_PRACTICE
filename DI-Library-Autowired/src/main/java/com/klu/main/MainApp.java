package com.klu.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.klu.config.AppConfig;
import com.klu.model.Library;
public class MainApp {
	public static void main(String[] args) {
		//Load Spring IoC container
		
		ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
		
		// Retrieve Library bean
		
		Library library = context.getBean(Library.class);
		
		//Print all details
		library.displayDetails();
	}
}
